package com.example.bookshopmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseDomain {

    @Version
    private Long version;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "UPDATED")
    private Date updated;

    @PrePersist
    public void onCreate(){
        this.created = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }
}
