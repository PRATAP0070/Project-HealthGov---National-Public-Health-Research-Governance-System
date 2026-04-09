package com.healthgov.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthgov.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long citizenId;
	private String name;
	private Date dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String address;
	private String contactInfo;
	private String status;

	@OneToOne(mappedBy = "citizen", cascade = CascadeType.ALL)
	private HealthProfile healthProfile;

	@OneToMany(mappedBy = "citizen")
	private List<CitizenDocument> documents;

	@OneToMany(mappedBy = "citizen")
    @JsonIgnore
	private List<Enrollment> enrollments;
}
