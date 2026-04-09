package com.healthgov.dto;

import java.time.LocalDate;

import com.healthgov.enums.AuditStatus;
import com.healthgov.model.Users;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuditReponseDTO {
	private Long auditId;

	private Users officer;

	private String scope;

	private String findings;

	private LocalDate date;

	@Column(nullable = false)
	private AuditStatus status;
}
