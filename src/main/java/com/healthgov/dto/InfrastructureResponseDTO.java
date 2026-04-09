package com.healthgov.dto;

import com.healthgov.enums.InfrastructureType;

import lombok.Data;

@Data
public class InfrastructureResponseDTO {
	private Long infraId;
	private Long programId;
	private InfrastructureType type;
	private String location;
	private Integer capacity;
	private String status;
}
