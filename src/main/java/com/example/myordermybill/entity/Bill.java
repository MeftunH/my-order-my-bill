package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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

    @Column(name="TOTAL_BILL",precision = 15, scale=2,nullable = false)
    private BigDecimal totalBill;

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

    @Override
    public String toString() {
        return "Bill{"+
                "id="+id+
                ", totalBill="+totalBill+
                ", userId="+userId+
                ", company="+company+
                ", user="+user+
                '}';
    }
}
