package com.example.myordermybill.repository;

import com.example.myordermybill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
