package com.healthgov.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.healthgov.dto.ResourceCreateRequestDTO;
import com.healthgov.dto.ResourceResponseDTO;
import com.healthgov.service.ResourcesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    @PostMapping
    public ResourceResponseDTO create(@RequestBody ResourceCreateRequestDTO dto) {
        return resourcesService.create(dto);
    }

    @GetMapping("/{resourceId}")
    public ResourceResponseDTO getById(@PathVariable Long resourceId) {
        return resourcesService.getById(resourceId);
    }

    @GetMapping("/program/{programId}")
    public List<ResourceResponseDTO> getByProgramId(@PathVariable Long programId) {
        return resourcesService.getByProgramId(programId);
    }

    @PutMapping("/{resourceId}")
    public ResourceResponseDTO update(@PathVariable Long resourceId,
                                      @RequestBody ResourceCreateRequestDTO dto) {
        return resourcesService.update(resourceId, dto);
    }

    @DeleteMapping("/{resourceId}")
    public void delete(@PathVariable Long resourceId) {
        resourcesService.delete(resourceId);
    }
}