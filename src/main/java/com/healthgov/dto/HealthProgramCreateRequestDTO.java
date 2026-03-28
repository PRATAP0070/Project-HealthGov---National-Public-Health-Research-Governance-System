package com.healthgov.dto;

import java.time.LocalDate;
import java.util.Date;

import com.healthgov.enums.ProgramStatus;

import lombok.Data;

@Data
public class HealthProgramCreateRequestDTO {

	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double budget;
	private ProgramStatus status;
}
