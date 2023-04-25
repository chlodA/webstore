package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;
import com.example.SpringApp.repository.ProductRepository;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.lang.RuntimeException;

import java.util.List;
import java.util.Map;
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
        Optional<Product> savedProduct = productRepository. findByProductName(product.getName());
        if(savedProduct.isPresent()){
            throw new RuntimeException("Product already exist with given name:" + product.getName());
        }
        return this.productRepository.save(product);
    }
    @Override
    public  Product update(Product product){
        return this.productRepository.save(product);
    }

   // @Override
   // public Product findByItemNumber(String itemNumber) {
   //     return productRepository.findByItemNumber(itemNumber);
  //  }

    @Override
    public Product getProductById(Long id) {
        Optional< Product > optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id: " + id);
        }
        return product;
    }
    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public Optional<Product> findByProductName(String productName){
        return productRepository.findByProductName(productName);
    }

  /*  @Override
    public List<Product> filterProducts(String category, Map<String, List<String>> filterParams) {

        return productRepository.filterProducts(category, filterParams);
    }*/

    public List<Product> getProductsByFilter(String category, String manufacturer) {

        return productRepository.findByCategoryAndManufacturer(category, manufacturer);
    }



    /* TO DO
     *  Delete an item based on Id
     * Throw 404 exception if not found*/
    @Override
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

}
