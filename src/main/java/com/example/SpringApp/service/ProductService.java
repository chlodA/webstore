package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;

import java.util.List;
//@Service
public interface ProductService {

    List<Product> getProducts();
    void saveProduct(Product product);
  /*  List<Product> getProductsByName(String productName);*/
  Product findByProductId(String productID);
    Product getProductById(Long id);
    void deleteProductById(Long id);



}
