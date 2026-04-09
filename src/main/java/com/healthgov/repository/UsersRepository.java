package com.healthgov.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgov.enums.UserRole;
import com.healthgov.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUserIdAndRole(Long userId, UserRole role);
}
