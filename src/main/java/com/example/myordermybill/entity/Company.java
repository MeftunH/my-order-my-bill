package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name = "company")
@Entity
@Getter
@Setter
public class Company extends Customer{
    @Id
    @SequenceGenerator(name ="COMPANY" ,sequenceName = "COMPANY_ID_SEQ")
    @GeneratedValue(generator = "COMPANY")
    private Long id;

    //One to Many to Bill
    @OneToMany(mappedBy="company")
    private Set<Bill> bills;

    @Override
    public String toString() {
        return "Company{"+
                "id="+id+
                ", bills="+bills+
                '}';
    }
}
