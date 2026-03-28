package com.healthgov.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentCreateRequestDTO {

    @NotNull(message = "citizenId is required")
    private Long citizenId;

    @NotNull(message = "programId is required")
    private Long programId;

    // Optional: if not provided, set LocalDate.now() in service
    private LocalDate date;

    // Optional: if not provided, default to "PENDING" in service
    @NotBlank(message = "status is required")
    private String status;
}