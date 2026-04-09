package com.healthgov.dto;

import com.healthgov.enums.ResourceType;
import lombok.Data;

@Data
public class ResourceResponseDTO {
    private Long resourceId;
    private Long programId;
    private ResourceType type;
    private Integer quantity;
    private String status;
}