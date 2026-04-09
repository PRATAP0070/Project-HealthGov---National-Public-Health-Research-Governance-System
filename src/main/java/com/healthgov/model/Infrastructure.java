package com.healthgov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthgov.enums.InfrastructureType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Infrastructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infraId;
 
    @ManyToOne
    @JoinColumn(name = "programId")
    @JsonIgnore
    private HealthProgram program;
 
    @Enumerated(EnumType.STRING)
    private InfrastructureType type;
    
    private String location;
    private Integer capacity;
    private String status;
}
