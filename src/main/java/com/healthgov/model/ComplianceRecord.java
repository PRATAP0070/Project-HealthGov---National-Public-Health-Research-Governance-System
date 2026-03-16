package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ComplianceRecord {
    @Id
    private Long complianceId;
    private Long entityId;
    private String type;
    private String result;
    private Date date;
    private String notes;
}