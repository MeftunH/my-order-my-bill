package com.example.myordermybill.repository;

import com.example.myordermybill.entity.Bill;
import com.example.myordermybill.entity.Company;
import com.example.myordermybill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findBillsByUser(User u);
    //find Bills By Company
    List<Bill> findBillsByCompany(Company u);

    @Query(value = "SELECT b FROM Bill b WHERE MONTH(b.createdDate) = 6")
    List<Company> getBillsCreatedInJune();
}
