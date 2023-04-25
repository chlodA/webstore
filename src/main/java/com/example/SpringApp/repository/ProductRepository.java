package com.example.SpringApp.repository;

import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Product findByItemNumber(String itemNumber);
    List<Product> findByProductName(String productName);
    //"SELECT * FROM PRODUCTS WHERE CATEGORY =:category"
    List<Product> findByCategory(String category);
    //@Query("SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories ) AND MANUFACTURER IN ( :brands)")

   // List <Product> filterProducts(String category, Map<String,List<String>> filterParams);

    List<Product> findByCategoryAndManufacturer(String category, String manufacturer);
}