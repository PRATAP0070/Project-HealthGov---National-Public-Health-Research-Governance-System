package com.healthgov.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.healthgov.model.Infrastructure;

public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {
    List<Infrastructure> findByProgram_ProgramId(Long programId);
}