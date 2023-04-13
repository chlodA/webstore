package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    void addProduct(Product product);

    List<Product> getProductsByDescription(String description);

    Product getProductById(String productID);


}
