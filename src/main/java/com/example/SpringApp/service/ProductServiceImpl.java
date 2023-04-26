package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;
import com.example.SpringApp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
    @Override
    public  Product update(Product product){
        return this.productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional< Product > optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        }
        return product;

    }
    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<Product> findByProductName(String productName){
        return productRepository.findByProductName(productName);
    }

    public List<Product> getProductsByFilter(String category, String manufacturer) {

        return productRepository.findByCategoryAndManufacturer(category, manufacturer);
    }

    @Override
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

}
