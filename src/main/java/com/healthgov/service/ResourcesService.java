package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.ResourceCreateRequestDTO;
import com.healthgov.dto.ResourceResponseDTO;

public interface ResourcesService {
    ResourceResponseDTO create(ResourceCreateRequestDTO dto);
    ResourceResponseDTO getById(Long resourceId);
    List<ResourceResponseDTO> getByProgramId(Long programId);
    ResourceResponseDTO update(Long resourceId, ResourceCreateRequestDTO dto);
    void delete(Long resourceId);
}