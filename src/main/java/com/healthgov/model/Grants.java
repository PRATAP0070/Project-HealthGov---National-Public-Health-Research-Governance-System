package com.healthgov.model;

import java.time.LocalDate;

import com.healthgov.enums.GrantStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Grants {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long grantId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "project_Id", nullable = false)
	private ResearchProject project;

	@ManyToOne(optional = false)
	@JoinColumn(name = "researcher_Id", nullable = false)
	private Users researcher;

	private Double amount;

	@Column(name = "granted_at")
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private GrantStatus status;
}