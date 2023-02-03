package com.example.myordermybill.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Customer {
    @Column(name="NAME",length=30,unique=true,nullable = false)
    private String NAME;
}
