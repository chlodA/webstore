package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;

import java.util.List;
//@Service
public interface ProductService {

    List<Product> getAllProducts();
    Product save(Product product);
  /*  List<Product> getProductsByName(String productName);*/
    Product findByItemNumber(String productID);
    Product getProductById(Long id);
    Product deleteProductById(Long id);


}
