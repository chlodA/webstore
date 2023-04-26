package com.example.SpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Product already exist with given name.")
public class ProductAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID =
            3935230281455340039L;
    private String name;
/*    List<Product> savedProduct = productService.findByProductName(product.getName());

    public String getName(){
        return name;
    }*/
    public ProductAlreadyExistsException() {
        super(String.format("Product already exist with given name."));
    }
}
