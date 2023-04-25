package com.example.SpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

  /*  public ProductNotFoundException(Long id){
        super(String.format("Product with Id %d not found", id));
    }*/
  private Long id;

    public ProductNotFoundException(Long id){
        this.id =id;
    }

    public Long findByCategory(){
        return id;
    }
}
