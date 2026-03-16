package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enrollment {
    @Id
    private Long enrollmentId;
 
    @ManyToOne
    @JoinColumn(name = "citizenId")
    private Citizen citizen;
 
    @ManyToOne
    @JoinColumn(name = "programId")
    private HealthProgram program;
 
    private Date date;
    private String status;
}
