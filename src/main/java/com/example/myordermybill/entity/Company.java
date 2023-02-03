package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private Set<Bill> bills = new HashSet<>();

    @Override
    public String toString() {
        return "Company{"+
                "id="+id+
                ", bills="+bills+
                '}';
    }
}
