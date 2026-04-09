package com.healthgov.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthgov.dto.ResourceCreateRequestDTO;
import com.healthgov.dto.ResourceResponseDTO;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.HealthProgram;
import com.healthgov.model.Resources;
import com.healthgov.repository.HealthProgramRepository;
import com.healthgov.repository.ResourcesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepo;
    private final HealthProgramRepository programRepo;

    @Override
    public ResourceResponseDTO create(ResourceCreateRequestDTO dto) {
        HealthProgram program = programRepo.findById(dto.getProgramId())
            .orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + dto.getProgramId()));

        Resources r = new Resources();
        r.setProgram(program);
        r.setType(dto.getType());
        r.setQuantity(dto.getQuantity());
        r.setStatus(dto.getStatus());

        return toDto(resourcesRepo.save(r));
    }

    @Override
    @Transactional(readOnly = true)
    public ResourceResponseDTO getById(Long resourceId) {
        Resources r = resourcesRepo.findById(resourceId)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found id=" + resourceId));
        return toDto(r);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourceResponseDTO> getByProgramId(Long programId) {
        return resourcesRepo.findByProgram_ProgramId(programId)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public ResourceResponseDTO update(Long resourceId, ResourceCreateRequestDTO dto) {
        Resources r = resourcesRepo.findById(resourceId)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found id=" + resourceId));

        if (dto.getProgramId() != null) {
            HealthProgram program = programRepo.findById(dto.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + dto.getProgramId()));
            r.setProgram(program);
        }

        r.setType(dto.getType());
        r.setQuantity(dto.getQuantity());
        r.setStatus(dto.getStatus());

        return toDto(resourcesRepo.save(r));
    }

    @Override
    public void delete(Long resourceId) {
        if (!resourcesRepo.existsById(resourceId)) {
            throw new ResourceNotFoundException("Resource not found id=" + resourceId);
        }
        resourcesRepo.deleteById(resourceId);
    }

    private ResourceResponseDTO toDto(Resources r) {
        ResourceResponseDTO dto = new ResourceResponseDTO();
        dto.setResourceId(r.getResourceId());
        dto.setProgramId(r.getProgram() != null ? r.getProgram().getProgramId() : null);
        dto.setType(r.getType());
        dto.setQuantity(r.getQuantity());
        dto.setStatus(r.getStatus());
        return dto;
    }
}