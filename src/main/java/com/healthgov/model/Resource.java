package com.healthgov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Resource {
    @Id
    private Long resourceId;
 
    @ManyToOne
    @JoinColumn(name = "programId")
    private HealthProgram program;
 
    private String type;
    private Integer quantity;
    private String status;
}