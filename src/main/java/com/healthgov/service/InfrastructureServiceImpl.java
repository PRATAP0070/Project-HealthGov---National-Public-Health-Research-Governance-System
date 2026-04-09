package com.healthgov.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthgov.dto.InfrastructureCreateRequestDTO;
import com.healthgov.dto.InfrastructureResponseDTO;
import com.healthgov.exceptions.ResourceNotFoundException;
import com.healthgov.model.HealthProgram;
import com.healthgov.model.Infrastructure;
import com.healthgov.repository.HealthProgramRepository;
import com.healthgov.repository.InfrastructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InfrastructureServiceImpl implements InfrastructureService {

    private final InfrastructureRepository infraRepo;
    private final HealthProgramRepository programRepo;

    @Override
    public InfrastructureResponseDTO create(InfrastructureCreateRequestDTO dto) {
        HealthProgram program = programRepo.findById(dto.getProgramId())
            .orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + dto.getProgramId()));

        Infrastructure i = new Infrastructure();
        i.setProgram(program);
        i.setType(dto.getType());
        i.setLocation(dto.getLocation());
        i.setCapacity(dto.getCapacity());
        i.setStatus(dto.getStatus());

        return toDto(infraRepo.save(i));
    }

    @Override
    @Transactional(readOnly = true)
    public InfrastructureResponseDTO getById(Long infraId) {
        Infrastructure i = infraRepo.findById(infraId)
            .orElseThrow(() -> new ResourceNotFoundException("Infrastructure not found id=" + infraId));
        return toDto(i);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InfrastructureResponseDTO> getByProgramId(Long programId) {
        return infraRepo.findByProgram_ProgramId(programId)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public InfrastructureResponseDTO update(Long infraId, InfrastructureCreateRequestDTO dto) {
        Infrastructure i = infraRepo.findById(infraId)
            .orElseThrow(() -> new ResourceNotFoundException("Infrastructure not found id=" + infraId));

        if (dto.getProgramId() != null) {
            HealthProgram program = programRepo.findById(dto.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("HealthProgram not found id=" + dto.getProgramId()));
            i.setProgram(program);
        }

        i.setType(dto.getType());
        i.setLocation(dto.getLocation());
        i.setCapacity(dto.getCapacity());
        i.setStatus(dto.getStatus());

        return toDto(infraRepo.save(i));
    }

    @Override
    public void delete(Long infraId) {
        if (!infraRepo.existsById(infraId)) {
            throw new ResourceNotFoundException("Infrastructure not found id=" + infraId);
        }
        infraRepo.deleteById(infraId);
    }

    private InfrastructureResponseDTO toDto(Infrastructure i) {
        InfrastructureResponseDTO dto = new InfrastructureResponseDTO();
        dto.setInfraId(i.getInfraId());
        dto.setProgramId(i.getProgram() != null ? i.getProgram().getProgramId() : null);
        dto.setType(i.getType());
        dto.setLocation(i.getLocation());
        dto.setCapacity(i.getCapacity());
        dto.setStatus(i.getStatus());
        return dto;
    }
}