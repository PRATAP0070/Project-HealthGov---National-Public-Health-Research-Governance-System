package com.healthgov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthgov.model.Resources;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
	List<Resources> findByProgram_ProgramId(Long programId);
}
