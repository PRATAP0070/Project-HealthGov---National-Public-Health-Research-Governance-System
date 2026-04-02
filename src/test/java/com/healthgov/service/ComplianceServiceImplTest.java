package com.healthgov.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.healthgov.dto.ComplianceCreateRequest;
import com.healthgov.dto.ComplianceResponseDTO;
import com.healthgov.dto.ComplianceUpdateRequest;
import com.healthgov.enums.ComplianceResult;
import com.healthgov.enums.ComplianceType;
import com.healthgov.exceptions.ComplianceRequestException;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.ComplianceRecord;
import com.healthgov.repository.ComplianceRecordRepository;
import com.healthgov.repository.GrantsRepository;
import com.healthgov.repository.HealthProgramRepository;
import com.healthgov.repository.ResearchProjectRepository;

@ExtendWith(MockitoExtension.class)
class ComplianceServiceImplTest {

	@Mock
	ComplianceRecordRepository complianceRepo;

	@Mock
	HealthProgramRepository healthProgramRepo;

	@Mock
	ResearchProjectRepository researchProjectRepo;

	@Mock
	GrantsRepository grantsRepo;

	@InjectMocks
	ComplianceServiceImpl service;

	/* -------------------- CREATE -------------------- */

	@Test
	void createCompliance_success() {
		ComplianceCreateRequest req = new ComplianceCreateRequest();
		req.setType(ComplianceType.PROGRAM);
		req.setEntityId(1L);
		req.setResult("COMPLIANT");
		req.setDate(LocalDate.now());
		req.setNotes("All checks passed");

		when(healthProgramRepo.existsById(1L)).thenReturn(true);
		when(complianceRepo.existsByEntityIdAndType(1L, ComplianceType.PROGRAM)).thenReturn(false);
		when(complianceRepo.save(any())).thenAnswer(i -> {
			ComplianceRecord rec = i.getArgument(0);
			rec.setComplianceId(10L);
			return rec;
		});

		ComplianceResponseDTO dto = service.createRecord(req);

		assertNotNull(dto);
		assertEquals(10L, dto.getComplianceId());
		assertEquals(ComplianceResult.COMPLIANT, dto.getResult());
		verify(complianceRepo).save(any());
	}

	@Test
	void createCompliance_duplicate_shouldFail() {
		ComplianceCreateRequest req = new ComplianceCreateRequest();
		req.setType(ComplianceType.PROGRAM);
		req.setEntityId(1L);
		req.setResult("COMPLIANT");
		req.setNotes("test");

		when(healthProgramRepo.existsById(1L)).thenReturn(true);
		when(complianceRepo.existsByEntityIdAndType(1L, ComplianceType.PROGRAM)).thenReturn(true);

		assertThrows(ComplianceRequestException.class, () -> service.createRecord(req));
	}

	/* -------------------- GET -------------------- */

	@Test
	void getOneByEntityIdAndType_success() {
		ComplianceRecord record = new ComplianceRecord();
		record.setComplianceId(5L);
		record.setEntityId(2L);
		record.setType(ComplianceType.PROJECT);
		record.setResult(ComplianceResult.PARTIAL);
		record.setDate(LocalDate.now());
		record.setNotes("Pending docs");

		when(researchProjectRepo.existsById(2L)).thenReturn(true);
		when(complianceRepo.findOneByEntityIdAndType(2L, ComplianceType.PROJECT)).thenReturn(Optional.of(record));

		ComplianceResponseDTO dto = service.getOneByEntityIdAndType(ComplianceType.PROJECT, 2L);

		assertEquals(5L, dto.getComplianceId());
		assertEquals(ComplianceResult.PARTIAL, dto.getResult());
	}

	/* -------------------- UPDATE -------------------- */

	@Test
	void updateExisting_success() {
		ComplianceUpdateRequest dto = new ComplianceUpdateRequest();
		dto.setResult("NON_COMPLIANT");
		dto.setNotes("Audit failed");
		dto.setDate(LocalDate.now());

		ComplianceRecord record = new ComplianceRecord();
		record.setComplianceId(8L);
		record.setEntityId(3L);
		record.setType(ComplianceType.GRANT);

		when(grantsRepo.existsById(3L)).thenReturn(true);
		when(complianceRepo.findOneByEntityIdAndType(3L, ComplianceType.GRANT)).thenReturn(Optional.of(record));
		when(complianceRepo.save(any())).thenReturn(record);

		ComplianceResponseDTO response = service.updateExisting(ComplianceType.GRANT, 3L, dto);

		assertEquals(8L, response.getComplianceId());
		assertEquals(ComplianceResult.NON_COMPLIANT, response.getResult());
	}

	/* -------------------- DELETE -------------------- */

	@Test
	void deleteById_success() {
		ComplianceRecord record = new ComplianceRecord();
		record.setComplianceId(99L);

		when(complianceRepo.findById(99L)).thenReturn(Optional.of(record));

		ComplianceResponseDTO dto = service.deleteById(99L);

		assertEquals(99L, dto.getComplianceId());
		verify(complianceRepo).deleteById(99L);
	}

	@Test
	void deleteById_notFound() {
		when(complianceRepo.findById(100L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> service.deleteById(100L));
	}

	/* -------------------- VALIDATION -------------------- */

	@Test
	void invalidResult_shouldFail() {
		ComplianceCreateRequest req = new ComplianceCreateRequest();
		req.setType(ComplianceType.PROGRAM);
		req.setEntityId(1L);
		req.setResult("INVALID");

		when(healthProgramRepo.existsById(1L)).thenReturn(true);

		assertThrows(ComplianceRequestException.class, () -> service.createRecord(req));
	}
}