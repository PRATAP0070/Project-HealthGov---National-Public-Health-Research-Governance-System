package com.healthgov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class HealthProfile {
    @Id
    private Long profileId;
 
    @OneToOne
    @JoinColumn(name = "citizenId")
    private Citizen citizen;
 
    @Lob ////recheck
    private String medicalHistoryJSON;
    private String allergies;
    private String status;
}
