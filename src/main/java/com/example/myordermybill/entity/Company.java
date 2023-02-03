package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name = "company")
@Entity
@Getter
@Setter
public class Company {
    @Id
    @SequenceGenerator(name ="COMPANY" ,sequenceName = "COMPANY_ID_SEQ")
    @GeneratedValue(generator = "COMPANY")
    private Long id;

    @Column(name="NAME",length=30,unique=true,nullable = false)
    private String name;

    //One to Many to Bill
    @OneToMany(mappedBy="company")
    private Set<Bill> bills;
}
