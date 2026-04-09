package com.healthgov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgov.model.ResearchProject;

public interface ResearchProjectRepository extends JpaRepository<ResearchProject, Long> {

}
