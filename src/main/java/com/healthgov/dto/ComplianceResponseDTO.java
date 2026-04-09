package com.healthgov.dto;

import java.time.LocalDate;

import com.healthgov.enums.ComplianceResult;
import com.healthgov.enums.ComplianceType;

import lombok.Data;

@Data
public class ComplianceResponseDTO {
	private Long complianceId;

	private Long entityId;

	private ComplianceType type;

	private ComplianceResult result;

	private LocalDate date;

	private String notes;
}
