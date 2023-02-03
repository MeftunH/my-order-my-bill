package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Table(name="product")
@Entity
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(name ="CUSTOMER" ,sequenceName = "CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "CUSTOMER")
    private Long id;
    @Column(name="NAME",length=30,unique=true,nullable = false)
    private String name;

    @Column(name = "PRICE",precision = 15, scale=2,nullable = false)
    private BigDecimal price;

    @ManyToMany
    Set<Bill> bills;

}
