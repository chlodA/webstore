package com.example.SpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No products found")
public class ProductNotFoundException extends RuntimeException{
        private static final long serialVersionUID =
        3935230281455340039L;
        public ProductNotFoundException() {
                super(String.format("Product not found"));
        }

}
