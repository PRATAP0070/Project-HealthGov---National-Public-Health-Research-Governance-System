package com.healthgov.model;

import java.time.LocalDate;
import java.util.List;

import com.healthgov.enums.ProgramStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class HealthProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long programId;

	private String title;
	private String description;

	private LocalDate startDate;
	private LocalDate endDate;

	private Double budget;

	@Enumerated(EnumType.STRING)
	private ProgramStatus status;

	@OneToMany(mappedBy = "program")
	private List<Enrollment> enrollments;

	@OneToMany(mappedBy = "program")
	private List<Resources> resources;

	@OneToMany(mappedBy = "program")
	private List<Infrastructure> infrastructures;
}