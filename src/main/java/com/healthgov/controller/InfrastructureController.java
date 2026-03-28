package com.healthgov.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.healthgov.dto.InfrastructureCreateRequestDTO;
import com.healthgov.dto.InfrastructureResponseDTO;
import com.healthgov.service.InfrastructureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/infrastructures")
@RequiredArgsConstructor
public class InfrastructureController {

    private final InfrastructureService infrastructureService;

    @PostMapping
    public InfrastructureResponseDTO create(@RequestBody InfrastructureCreateRequestDTO dto) {
        return infrastructureService.create(dto);
    }

    @GetMapping("/{infraId}")
    public InfrastructureResponseDTO getById(@PathVariable Long infraId) {
        return infrastructureService.getById(infraId);
    }

    @GetMapping("/program/{programId}")
    public List<InfrastructureResponseDTO> getByProgramId(@PathVariable Long programId) {
        return infrastructureService.getByProgramId(programId);
    }

    @PutMapping("/{infraId}")
    public InfrastructureResponseDTO update(@PathVariable Long infraId,
                                            @RequestBody InfrastructureCreateRequestDTO dto) {
        return infrastructureService.update(infraId, dto);
    }

    @DeleteMapping("/{infraId}")
    public void delete(@PathVariable Long infraId) {
        infrastructureService.delete(infraId);
    }
}