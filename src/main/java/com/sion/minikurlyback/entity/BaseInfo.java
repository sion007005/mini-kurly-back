package com.sion.minikurlyback.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class BaseInfo {
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;
    private Boolean deleted;
}
