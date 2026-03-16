package com.healthgov.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Notification {
    @Id
    private Long notificationId;
 
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
 
    private Long entityId;
    private String message;
    private String category;
    private String status;
    private Date createdDate;
}