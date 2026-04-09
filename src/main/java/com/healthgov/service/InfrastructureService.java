package com.healthgov.service;

import java.util.List;
import com.healthgov.dto.InfrastructureCreateRequestDTO;
import com.healthgov.dto.InfrastructureResponseDTO;

public interface InfrastructureService {
    InfrastructureResponseDTO create(InfrastructureCreateRequestDTO dto);
    InfrastructureResponseDTO getById(Long infraId);
    List<InfrastructureResponseDTO> getByProgramId(Long programId);
    InfrastructureResponseDTO update(Long infraId, InfrastructureCreateRequestDTO dto);
    void delete(Long infraId);
}