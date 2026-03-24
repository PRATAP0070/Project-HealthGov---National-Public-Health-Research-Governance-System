package com.healthgov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthgov.enums.AuditStatus;
import com.healthgov.model.Audit;

@Repository
@Transactional
public interface AuditRepository extends JpaRepository<Audit, Long> {

	List<Audit> findByStatus(AuditStatus status);

	List<Audit> findByOfficer_UserId(Long officerId);

	boolean existsByOfficer_UserIdAndScopeIgnoreCase(Long userId, String scope);

}
