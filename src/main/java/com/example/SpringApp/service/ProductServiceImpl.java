package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;
import com.example.SpringApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
@Autowired
    private ProductRepository productRepository;

  /*  public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }*/

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

   /* public Product getProduct(Integer id) {
        return productRepository.findByIdAndActive(id,true).orElseThrow(NotFoundException::new);
    }*/

    @Override
    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

 /*   @Override
    public List<Product> getProductsByCategory(String description) {
        return null;
    }*/
@Override
    public List<Product> getProductsByDescription(String description){
        return productRepository.getProductsByDescription(description);
    }

    public Product getProductById(String productID){
        return productRepository.getProductById(productID);
    }

    /*public Product addProd(Product product){
        return productRepository.save(product);
    }*/

/*    @Override
    public void updateAllStock() {
        List<Product> allProducts =
                productRepository.getAllProducts();
        for(Product product : allProducts) {
            if(product.getUnitsInStock()<500)
                productRepository.updateStock
                        (product.getProductId(),
                                product.getUnitsInStock()+1000);
        }
    }*/


}
