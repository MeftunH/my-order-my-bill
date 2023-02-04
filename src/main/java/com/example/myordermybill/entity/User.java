package com.example.myordermybill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="USERS")
@Getter
@Setter
public class User{
    public User(String name, String username, Date date) {
        this.username=username;
        this.name = name;
        this.createdDate=date;
    }

    public User() {
    }

    @Column(name="NAME",length=30,nullable = false)
    private String name;
    @Id
    @SequenceGenerator(name ="User" ,sequenceName = "USER_ID_SEQ")
    @GeneratedValue(generator = "User")
    private Long id;

    @Column(name="USERNAME",length=30,nullable = false)
    private String username;

    @OneToMany(mappedBy="user")
    private Set<Bill> bills;

    @CreatedDate
    @Column(name="CREATED_DATE")
    private java.util.Date createdDate;
    @Override
    public String toString() {
        return "User{"+
                "id="+id+
                ", username='"+username+'\''+
                ", bills="+bills+
                '}';
    }

}
