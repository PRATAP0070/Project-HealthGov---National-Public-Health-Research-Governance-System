package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.HealthProgramCreateRequestDTO;
import com.healthgov.dto.HealthProgramResponseDTO;

public interface HealthProgramService {
	HealthProgramResponseDTO create(HealthProgramCreateRequestDTO dto);

	HealthProgramResponseDTO getById(Long programId);

	List<HealthProgramResponseDTO> getAll();

	HealthProgramResponseDTO update(Long programId, HealthProgramCreateRequestDTO dto);

	void delete(Long programId);
}
