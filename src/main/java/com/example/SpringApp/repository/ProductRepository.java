package com.example.SpringApp.repository;

import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //List<Product> getProductsByName(String productName);
    //SELECT * FROM PRODUCTS WHERE ID = :id";
    Product findByItemNumber(String itemNumber);

    //Optional<Product> findByName(String productName);
}