package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long auditId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users user;

	@NotBlank(message = "Action is mandatory")
	private String action;

	@NotBlank(message = "Resource is mandatory")
	private String resource;

	@NotNull(message = "Timestamp is mandatory")
	private Date timestamp;
}
