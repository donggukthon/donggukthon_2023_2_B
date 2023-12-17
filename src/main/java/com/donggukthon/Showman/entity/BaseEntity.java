package com.donggukthon.Showman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.modifiedAt = this.createdAt;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.modifiedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}