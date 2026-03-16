package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class GrantApplication {
    @Id
    private Long applicationId;
 
    @ManyToOne
    @JoinColumn(name = "researcherId")
    private User researcher;
 
    @ManyToOne
    @JoinColumn(name = "projectId")
    private ResearchProject project;
 
    private Date submittedDate;
    private String status;
}