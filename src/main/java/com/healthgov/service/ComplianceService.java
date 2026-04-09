package com.healthgov.service;

import java.util.List;

import com.healthgov.dto.ComplianceCreateRequest;
import com.healthgov.dto.ComplianceResponseDTO;
import com.healthgov.dto.ComplianceUpdateRequest;
import com.healthgov.enums.ComplianceType;

public interface ComplianceService {

	List<ComplianceResponseDTO> getAllComplianceRecords();

	ComplianceResponseDTO getOneByEntityIdAndType(ComplianceType type, Long entityId);

	ComplianceResponseDTO createRecord(ComplianceCreateRequest complianceRecord);

	ComplianceResponseDTO updateExisting(ComplianceType type, Long entityId, ComplianceUpdateRequest dto);

	ComplianceResponseDTO updateResultByEntityIdAndType(ComplianceType type, Long entityId, String result);

	ComplianceResponseDTO updateNotesByEntityIdAndType(ComplianceType type, Long entityId, String notes);

	ComplianceResponseDTO deleteById(Long id);
}