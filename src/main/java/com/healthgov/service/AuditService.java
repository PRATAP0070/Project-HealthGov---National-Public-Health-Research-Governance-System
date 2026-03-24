package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.AuditCreateRequest;
import com.healthgov.dto.AuditUpdateRequest;
import com.healthgov.model.Audit;

public interface AuditService {

	List<Audit> getAllAudits();
	Audit createAudit(AuditCreateRequest request);

	Audit updateAudit(Long auditId, AuditUpdateRequest request);

	Audit updateStatus(Long auditId, String status);

	Audit updateFindings(Long auditId, String findings);

	Audit getAudit(Long auditId);
}