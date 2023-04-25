package com.example.SpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No products found under this category")
public class NoProductsFoundException extends RuntimeException{
        private static final long serialVersionUID =
        3935230281455340039L;

        public NoProductsFoundException() {
                super(String.format("Product not found"));
        }
       /* private String category;

        public NoProductsFoundException(String category){
                this.category=category;
        }

        public String findByCategory(){
                return category;
        }*/
}
