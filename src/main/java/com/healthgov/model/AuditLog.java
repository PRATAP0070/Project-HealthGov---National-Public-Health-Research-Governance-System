package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AuditLog {
    @Id
    private Long auditId;
 
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
 
    private String action;
    private String resource;
    private Date timestamp;
}
