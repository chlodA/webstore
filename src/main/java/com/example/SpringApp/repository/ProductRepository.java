package com.example.SpringApp.repository;

import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //SELECT * FROM PRODUCTS WHERE ID = :id";
    Product findByItemNumber(String itemNumber);
    Optional<Product> findByProductName(String productName);

    List<Product> getProductsByCategory(String category);

}