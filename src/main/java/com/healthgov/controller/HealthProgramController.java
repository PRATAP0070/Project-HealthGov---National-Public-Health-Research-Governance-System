package com.healthgov.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.healthgov.dto.HealthProgramCreateRequestDTO;
import com.healthgov.dto.HealthProgramResponseDTO;
import com.healthgov.service.HealthProgramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class HealthProgramController {

    private final HealthProgramService programService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HealthProgramResponseDTO create(@RequestBody HealthProgramCreateRequestDTO dto) {
        return programService.create(dto);
    }

    @GetMapping("/{programId}")
    public HealthProgramResponseDTO getById(@PathVariable Long programId) {
        return programService.getById(programId);
    }

    @GetMapping
    public List<HealthProgramResponseDTO> getAll() {
        return programService.getAll();
    }

    @PutMapping("/{programId}")
    public HealthProgramResponseDTO update(@PathVariable Long programId,
                                          @RequestBody HealthProgramCreateRequestDTO dto) {
        return programService.update(programId, dto);
    }

    @DeleteMapping("/{programId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long programId) {
        programService.delete(programId);
    }
}