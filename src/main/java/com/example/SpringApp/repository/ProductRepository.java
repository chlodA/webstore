package com.example.SpringApp.repository;

import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);
    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndManufacturer(String category, String manufacturer);
}