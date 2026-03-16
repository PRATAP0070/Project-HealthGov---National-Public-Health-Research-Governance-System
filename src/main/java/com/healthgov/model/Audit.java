package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Audit {
    @Id
    private Long auditId;
 
    @ManyToOne
    @JoinColumn(name = "officerId")
    private User officer;
 
    private String scope;
    private String findings;
    private Date date;
    private String status;
}
