package com.example.myordermybill.repository;

import com.example.myordermybill.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
