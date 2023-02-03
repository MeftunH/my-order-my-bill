package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="USER")
@Getter
@Setter
public class User {
    @Id
    @SequenceGenerator(name ="User" ,sequenceName = "USER_ID_SEQ")
    @GeneratedValue(generator = "User")
    private Long id;

    @Column(name="NAME",length=30,unique=true,nullable = false)
    private String NAME;

    @Column(name="USERNAME",length=30,unique=true,nullable = false)
    private String username;

    @OneToMany(mappedBy="user")
    private Set<Bill> bills;

    @Override
    public String toString() {
        return "User{"+
                "id="+id+
                ", NAME='"+NAME+'\''+
                ", username='"+username+'\''+
                ", bills="+bills+
                '}';
    }
}
