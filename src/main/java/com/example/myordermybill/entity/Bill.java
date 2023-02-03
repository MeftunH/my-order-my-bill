package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name="bill")
@Entity
@Getter
@Setter
public class Bill {
    @Id
    @SequenceGenerator(name ="BILL" ,sequenceName = "BILL_ID_SEQ")
    @GeneratedValue(generator = "BILL")
    private Long id;

    //Many to One to User
    @ManyToOne
    @JoinColumn(name="USER_ID",nullable = false)
    @MapsId("USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


}
