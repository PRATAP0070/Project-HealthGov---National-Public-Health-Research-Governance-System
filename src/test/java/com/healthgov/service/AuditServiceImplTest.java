package com.healthgov.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.healthgov.dto.AuditCreateRequest;
import com.healthgov.dto.AuditReponseDTO;
import com.healthgov.dto.AuditUpdateRequest;
import com.healthgov.enums.AuditStatus;
import com.healthgov.enums.UserRole;
import com.healthgov.exceptions.AuditRequestException;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.Audit;
import com.healthgov.model.Users;
import com.healthgov.repository.AuditRepository;
import com.healthgov.repository.GrantsRepository;
import com.healthgov.repository.HealthProgramRepository;
import com.healthgov.repository.ResearchProjectRepository;
import com.healthgov.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    AuditRepository auditRepo;

    @Mock
    UsersRepository usersRepo;

    @Mock
    HealthProgramRepository healthProgramRepo;

    @Mock
    ResearchProjectRepository researchProjectRepo;

    @Mock
    GrantsRepository grantsRepo;

    @InjectMocks
    AuditServiceImpl service;

    /* ---------------- CREATE AUDIT ---------------- */

    @Test
    void createAudit_success() {
        Users officer = new Users();
        officer.setUserId(1L);
        officer.setRole(UserRole.COMPLIANCE);

        AuditCreateRequest request = new AuditCreateRequest();
        request.setOfficerId(1L);
        request.setScope("PROGRAM:10");
        request.setFindings("All conditions satisfied");
        request.setDate(LocalDate.now());
        request.setStatus(AuditStatus.SCHEDULED);

        when(usersRepo.findByUserIdAndRole(1L, UserRole.COMPLIANCE))
                .thenReturn(Optional.of(officer));
        when(healthProgramRepo.existsById(10L))
                .thenReturn(true);
        when(auditRepo.existsByOfficer_UserIdAndScopeIgnoreCase(1L, "PROGRAM:10"))
                .thenReturn(false);
        when(auditRepo.save(any())).thenAnswer(i -> {
            Audit a = i.getArgument(0);
            a.setAuditId(100L);
            return a;
        });

        AuditReponseDTO dto = service.createAudit(request);

        assertNotNull(dto);
        assertEquals(100L, dto.getAuditId());
        assertEquals(AuditStatus.SCHEDULED, dto.getStatus());
        verify(auditRepo).save(any());
    }

    @Test
    void createAudit_invalidOfficer_shouldFail() {
        AuditCreateRequest request = new AuditCreateRequest();
        request.setOfficerId(99L);

        when(usersRepo.findByUserIdAndRole(99L, UserRole.COMPLIANCE))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.createAudit(request));
    }

    /* ---------------- UPDATE AUDIT ---------------- */

    @Test
    void updateAudit_success() {
        Audit audit = new Audit();
        audit.setAuditId(10L);

        AuditUpdateRequest request = new AuditUpdateRequest();
        request.setStatus(AuditStatus.COMPLETED);
        request.setFindings("Completed successfully");
        request.setDate(LocalDate.now());

        when(auditRepo.findById(10L)).thenReturn(Optional.of(audit));
        when(auditRepo.save(any())).thenReturn(audit);

        AuditReponseDTO dto = service.updateAudit(10L, request);

        assertEquals(AuditStatus.COMPLETED, dto.getStatus());
        verify(auditRepo).save(audit);
    }

    @Test
    void updateAudit_notFound_shouldFail() {
        when(auditRepo.findById(10L)).thenReturn(Optional.empty());

        AuditUpdateRequest req = new AuditUpdateRequest();

        assertThrows(ResourceNotFoundException.class,
                () -> service.updateAudit(10L, req));
    }

    /* ---------------- UPDATE STATUS ---------------- */

    @Test
    void updateStatus_success() {
        Audit audit = new Audit();
        audit.setAuditId(5L);

        when(auditRepo.findById(5L)).thenReturn(Optional.of(audit));
        when(auditRepo.save(any())).thenReturn(audit);

        AuditReponseDTO dto = service.updateStatus(5L, "IN_REVIEW");

        assertEquals(AuditStatus.IN_REVIEW, dto.getStatus());
    }

    /* ---------------- UPDATE FINDINGS ---------------- */

    @Test
    void updateFindings_success() {
        Audit audit = new Audit();
        audit.setAuditId(7L);

        when(auditRepo.findById(7L)).thenReturn(Optional.of(audit));
        when(auditRepo.save(any())).thenReturn(audit);

        AuditReponseDTO dto =
                service.updateFindings(7L, "Updated findings");

        assertEquals("Updated findings", dto.getFindings());
    }

    /* ---------------- GET AUDIT ---------------- */

    @Test
    void getAudit_success() {
        Audit audit = new Audit();
        audit.setAuditId(3L);

        when(auditRepo.findById(3L)).thenReturn(Optional.of(audit));

        AuditReponseDTO dto = service.getAudit(3L);

        assertEquals(3L, dto.getAuditId());
    }

    @Test
    void getAudit_nullId_shouldFail() {
        assertThrows(AuditRequestException.class,
                () -> service.getAudit(null));
    }

    /* ---------------- GET BY OFFICER ---------------- */

    @Test
    void getAllAuditsByOfficer_success() {
        Audit audit = new Audit();

        when(auditRepo.findByOfficer_UserId(1L))
                .thenReturn(List.of(audit));

        List<AuditReponseDTO> result =
                service.getAllAuditsByOfficer(1L);

        assertEquals(1, result.size());
    }

    /* ---------------- STATUS VALIDATION ---------------- */

    @Test
    void invalidStatus_shouldFail() {
        Audit audit = new Audit();
        when(auditRepo.findById(1L))
                .thenReturn(Optional.of(audit));

        assertThrows(AuditRequestException.class,
                () -> service.updateStatus(1L, "INVALID"));
    }
}