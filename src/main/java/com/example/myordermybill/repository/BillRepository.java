package com.example.myordermybill.repository;

import com.example.myordermybill.entity.Bill;
import com.example.myordermybill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findBillsByUser(User u);
}
