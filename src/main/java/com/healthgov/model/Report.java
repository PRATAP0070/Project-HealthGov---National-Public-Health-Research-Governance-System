package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Report {
    @Id
    private Long reportId;
    private String scope;
    private String metrics;
    private Date generatedDate;
}
 
