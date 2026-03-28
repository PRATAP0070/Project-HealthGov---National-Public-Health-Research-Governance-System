package com.healthgov.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthgov.dto.EnrollmentCreateRequestDTO;
import com.healthgov.dto.EnrollmentResponseDTO;
import com.healthgov.exceptions.ComplianceRequestException;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.Citizen;
import com.healthgov.model.Enrollment;
import com.healthgov.model.HealthProgram;
import com.healthgov.repository.CitizenRepository;
import com.healthgov.repository.EnrollmentRepository;
import com.healthgov.repository.HealthProgramRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepo;
	private final CitizenRepository citizenRepo;
	private final HealthProgramRepository programRepo;

	@Override
	public EnrollmentResponseDTO enroll(EnrollmentCreateRequestDTO dto) {
		if (dto == null)
			throw new ComplianceRequestException("Request body is required.");

		if (enrollmentRepo.existsByCitizen_CitizenIdAndProgram_ProgramId(dto.getCitizenId(), dto.getProgramId())) {
			throw new ComplianceRequestException("Citizen already enrolled in this program.");
		}

		Citizen citizen = citizenRepo.findById(dto.getCitizenId())
				.orElseThrow(() -> new ResourceNotFoundException("Citizen not found id=" + dto.getCitizenId()));

		HealthProgram program = programRepo.findById(dto.getProgramId())
				.orElseThrow(() -> new ResourceNotFoundException("Program not found id=" + dto.getProgramId()));

		Enrollment e = new Enrollment();
		e.setCitizen(citizen);
		e.setProgram(program);
		e.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());
		e.setStatus(dto.getStatus() != null ? dto.getStatus().trim() : "PENDING");

		return toDto(enrollmentRepo.save(e));
	}

	@Override
	@Transactional(readOnly = true)
	public List<EnrollmentResponseDTO> getByProgramId(Long programId) {
		return enrollmentRepo.findByProgram_ProgramId(programId).stream().map(this::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<EnrollmentResponseDTO> getByCitizenId(Long citizenId) {
		return enrollmentRepo.findByCitizen_CitizenId(citizenId).stream().map(this::toDto).toList();
	}

	@Override
	public EnrollmentResponseDTO updateStatus(Long enrollmentId, String status) {
		Enrollment e = enrollmentRepo.findById(enrollmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrollment not found id=" + enrollmentId));
		e.setStatus(status.trim());
		return toDto(enrollmentRepo.save(e));
	}

	@Override
	public void cancel(Long enrollmentId) {
		if (!enrollmentRepo.existsById(enrollmentId)) {
			throw new ResourceNotFoundException("Enrollment not found id=" + enrollmentId);
		}
		enrollmentRepo.deleteById(enrollmentId);
	}

	private EnrollmentResponseDTO toDto(Enrollment e) {
		EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
		dto.setEnrollmentId(e.getEnrollmentId());
		dto.setCitizenId(e.getCitizen().getCitizenId());
		dto.setCitizenName(e.getCitizen().getName());
		dto.setProgramId(e.getProgram().getProgramId());
		dto.setProgramTitle(e.getProgram().getTitle());
		dto.setDate(e.getDate());
		dto.setStatus(e.getStatus());
		return dto;
	}
}