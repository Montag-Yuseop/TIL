package com.test.tddtest.data.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updatedBy;
}
