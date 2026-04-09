package com.healthgov.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.healthgov.dto.EnrollmentCreateRequestDTO;
import com.healthgov.dto.EnrollmentResponseDTO;
import com.healthgov.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponseDTO enroll(@RequestBody EnrollmentCreateRequestDTO dto) {
        return enrollmentService.enroll(dto);
    }

    @GetMapping("/program/{programId}")
    public List<EnrollmentResponseDTO> getByProgram(@PathVariable Long programId) {
        return enrollmentService.getByProgramId(programId);
    }

    @GetMapping("/citizen/{citizenId}")
    public List<EnrollmentResponseDTO> getByCitizen(@PathVariable Long citizenId) {
        return enrollmentService.getByCitizenId(citizenId);
    }

    @PatchMapping("/{enrollmentId}/status")
    public EnrollmentResponseDTO updateStatus(@PathVariable Long enrollmentId,
                                             @RequestParam String status) {
        return enrollmentService.updateStatus(enrollmentId, status);
    }

    @DeleteMapping("/{enrollmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long enrollmentId) {
        enrollmentService.cancel(enrollmentId);
    }
}