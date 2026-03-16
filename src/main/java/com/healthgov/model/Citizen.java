package com.healthgov.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Citizen {
    @Id
    private Long citizenId;
    private String name;
    private Date dob;
    private String gender;
    private String address;
    private String contactInfo;
    private String status;
 
    @OneToOne(mappedBy = "citizen", cascade = CascadeType.ALL)
    private HealthProfile healthProfile;
 
    @OneToMany(mappedBy = "citizen")
    private List<CitizenDocument> documents;
 
    @OneToMany(mappedBy = "citizen")
    private List<Enrollment> enrollments;
}
