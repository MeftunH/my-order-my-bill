package com.example.myordermybill.repository;

import com.example.myordermybill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE MONTH(u.createdDate) = 6")
    List<User> getUsersCreatedInJune();
}
