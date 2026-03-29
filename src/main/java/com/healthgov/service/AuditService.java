package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.AuditCreateRequest;
import com.healthgov.dto.AuditReponseDTO;
import com.healthgov.dto.AuditUpdateRequest;
import com.healthgov.model.Audit;

public interface AuditService {

	List<AuditReponseDTO> getAllAudits();
	AuditReponseDTO createAudit(AuditCreateRequest request);

	AuditReponseDTO updateAudit(Long auditId, AuditUpdateRequest request);

	AuditReponseDTO updateStatus(Long auditId, String status);

	AuditReponseDTO updateFindings(Long auditId, String findings);

	AuditReponseDTO getAudit(Long auditId);
	
	List<AuditReponseDTO> getAllAuditsByOfficer(Long officerId);
}