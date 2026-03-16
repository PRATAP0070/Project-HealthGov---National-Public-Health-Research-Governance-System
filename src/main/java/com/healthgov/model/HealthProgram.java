package com.healthgov.model;

import java.sql.Date;


import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class HealthProgram {
    @Id
    private Long programId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double budget;
    private String status;
 
    @OneToMany(mappedBy = "program")
    private List<Enrollment> enrollments;
 
    @OneToMany(mappedBy = "program")
    private List<Resource> resources;
 
    @OneToMany(mappedBy = "program")
    private List<Infrastructure> infrastructures;
}
