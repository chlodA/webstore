package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;

import java.util.List;

//@Service
public interface ProductService {

    List<Product> getAllProducts();
    Product save(Product product);
    Product update(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    List<Product> findByCategory(String category);
    List<Product> findByProductName(String productName);
    Object getProductsByFilter(String category, String manufacturer);


}
