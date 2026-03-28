package com.healthgov.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthgov.dto.HealthProgramCreateRequestDTO;
import com.healthgov.dto.HealthProgramResponseDTO;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.HealthProgram;
import com.healthgov.repository.HealthProgramRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthProgramServiceImpl implements HealthProgramService {

	private final HealthProgramRepository programRepo;

	@Override
	public HealthProgramResponseDTO create(HealthProgramCreateRequestDTO dto) {
		HealthProgram p = new HealthProgram();
		p.setTitle(dto.getTitle());
		p.setDescription(dto.getDescription());
		p.setStartDate(dto.getStartDate());
		p.setEndDate(dto.getEndDate());
		p.setBudget(dto.getBudget());
		p.setStatus(dto.getStatus());
		return toDto(programRepo.save(p));
	}

	@Override
	@Transactional(readOnly = true)
	public HealthProgramResponseDTO getById(Long programId) {
		HealthProgram p = programRepo.findWithDetailsByProgramId(programId)
				.orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + programId));

		return toDtoWithDetails(p);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HealthProgramResponseDTO> getAll() {
		return programRepo.findAll().stream().map(this::toDtoWithDetails).collect(Collectors.toList());
	}

	@Override
	public HealthProgramResponseDTO update(Long programId, HealthProgramCreateRequestDTO dto) {
		HealthProgram p = programRepo.findById(programId)
				.orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + programId));

		p.setTitle(dto.getTitle());
		p.setDescription(dto.getDescription());
		p.setStartDate(dto.getStartDate());
		p.setEndDate(dto.getEndDate());
		p.setBudget(dto.getBudget());
		p.setStatus(dto.getStatus());

		return toDto(programRepo.save(p));
	}

	@Override
	public void delete(Long programId) {
		if (!programRepo.existsById(programId)) {
			throw new ResourceNotFoundException("HealthProgram not found id=" + programId);
		}
		programRepo.deleteById(programId);
	}

	// -------- MAPPERS --------

	private HealthProgramResponseDTO toDto(HealthProgram p) {
		HealthProgramResponseDTO dto = new HealthProgramResponseDTO();
		dto.setProgramId(p.getProgramId());
		dto.setTitle(p.getTitle());
		dto.setDescription(p.getDescription());
		dto.setStartDate(p.getStartDate());
		dto.setEndDate(p.getEndDate());
		dto.setBudget(p.getBudget());
		dto.setStatus(p.getStatus());
		return dto;
	}

	private HealthProgramResponseDTO toDtoWithDetails(HealthProgram p) {
		HealthProgramResponseDTO dto = toDto(p);

		dto.setEnrollments(
				p.getEnrollments().stream().map(e -> {
					HealthProgramResponseDTO.EnrollmentDTO ed = new HealthProgramResponseDTO.EnrollmentDTO();
					ed.setEnrollmentId(e.getEnrollmentId());
					ed.setCitizenId(e.getCitizen() != null ? e.getCitizen().getCitizenId() : null);
					ed.setCitizenName(e.getCitizen() != null ? e.getCitizen().getName() : null);
					ed.setEnrolledDate(e.getDate());
					ed.setStatus(e.getStatus());
					return ed;
				}).collect(Collectors.toList()));

		dto.setResources(p.getResources().stream().map(r -> {
			HealthProgramResponseDTO.ResourceDTO rd = new HealthProgramResponseDTO.ResourceDTO();
			rd.setResourceId(r.getResourceId());
			rd.setType(r.getType());
			rd.setQuantity(r.getQuantity());
			rd.setStatus(r.getStatus());
			return rd;
		}).collect(Collectors.toList()));

		dto.setInfrastructures(
				 p.getInfrastructures().stream().map(i -> {
					HealthProgramResponseDTO.InfrastructureDTO idto = new HealthProgramResponseDTO.InfrastructureDTO();
					idto.setInfraId(i.getInfraId());
					idto.setType(i.getType());
					idto.setLocation(i.getLocation());
					idto.setCapacity(i.getCapacity());
					idto.setStatus(i.getStatus());
					return idto;
				}).collect(Collectors.toList()));

		return dto;
	}
}