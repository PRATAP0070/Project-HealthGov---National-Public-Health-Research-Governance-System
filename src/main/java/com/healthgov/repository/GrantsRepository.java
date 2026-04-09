package com.healthgov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgov.model.Grants;

public interface GrantsRepository extends JpaRepository<Grants, Long> {

}
