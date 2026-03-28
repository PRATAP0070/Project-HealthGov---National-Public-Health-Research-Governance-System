package com.healthgov.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class EnrollmentResponseDTO {
	private Long enrollmentId;
	private Long citizenId;
	private String citizenName; // optional convenience
	private Long programId;
	private String programTitle; // optional convenience
	private LocalDate date;
	private String status;
}
