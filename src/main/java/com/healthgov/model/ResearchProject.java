package com.healthgov.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ResearchProject {
    @Id
    private Long projectId;
    private String title;
    private String description;
 
    @ManyToOne
    @JoinColumn(name = "researcherId")
    private User researcher;
 
    private Date startDate;
    private Date endDate;
    private String status;
 
    @OneToMany(mappedBy = "project")
    private List<GrantApplication> grantApplications;
 
    @OneToMany(mappedBy = "project")
    private List<Grant> grants;
}
