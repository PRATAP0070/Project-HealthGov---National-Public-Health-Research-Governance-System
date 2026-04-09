package com.healthgov.dto;

import com.healthgov.enums.InfrastructureType;

import lombok.Data;

@Data
public class InfrastructureCreateRequestDTO {
	private Long programId;
    private InfrastructureType type;
    private String location;
    private Integer capacity;
    private String status;
}
