package com.healthgov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthgov.model.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	List<Enrollment> findByProgram_ProgramId(Long programId);

	List<Enrollment> findByCitizen_CitizenId(Long citizenId);

	boolean existsByCitizen_CitizenIdAndProgram_ProgramId(Long citizenId, Long programId);
}
