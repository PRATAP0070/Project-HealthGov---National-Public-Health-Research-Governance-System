package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.EnrollmentCreateRequestDTO;
import com.healthgov.dto.EnrollmentResponseDTO;

public interface EnrollmentService {
	EnrollmentResponseDTO enroll(EnrollmentCreateRequestDTO dto);

	List<EnrollmentResponseDTO> getByProgramId(Long programId);

	List<EnrollmentResponseDTO> getByCitizenId(Long citizenId);

	EnrollmentResponseDTO updateStatus(Long enrollmentId, String status);

	void cancel(Long enrollmentId);
}
