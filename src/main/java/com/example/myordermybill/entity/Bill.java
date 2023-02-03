package com.example.myordermybill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Bill(Long id, BigDecimal totalBill, User userId, Company company, User user) {
        this.id=id;
        this.totalBill=totalBill;
        this.userId=userId;
        this.company=company;
        this.user=user;
    }

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
