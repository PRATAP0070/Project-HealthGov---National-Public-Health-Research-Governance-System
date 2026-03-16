package com.healthgov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Infrastructure {
    @Id
    private Long infraId;
 
    @ManyToOne
    @JoinColumn(name = "programId")
    private HealthProgram program;
 
    private String type;
    private String location;
    private Integer capacity;
    private String status;
}
