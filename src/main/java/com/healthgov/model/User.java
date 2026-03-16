package com.healthgov.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@NotNull(message = "User ID must not be null")
	private Long userId;
	
	@NotBlank(message = "Employee name cannot be null/balnk/null")
	@Size(max = 100, message = "Name cannot exceed 100 characters")
	@Column(nullable = false, length = 100)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Role role;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	@Size(max = 120, message = "Email cannot exceed 120 characters")
	@Column(nullable = false, unique = true, length = 120)
	private String email;

	@NotBlank(message = "Phone number cannot be blank")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	@Column(nullable = false, length = 10)
	private String phone;
	
	@Transient
	private String status;

	@OneToMany(mappedBy = "user")
	private List<AuditLog> auditLogs;

	@OneToMany(mappedBy = "user")
	private List<Notification> notifications;
}
