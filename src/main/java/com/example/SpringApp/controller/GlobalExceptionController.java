package com.example.SpringApp.controller;

import com.example.SpringApp.exceptions.NoProductsFoundUnderCategoryException;
import com.example.SpringApp.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidParameterException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoProductsFoundUnderCategoryException.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    NoProductsFoundUnderCategoryException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidCategoryName",
                exception.findByCategory());
        mav.addObject("exception", exception);
        mav.addObject("url",
                req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("error");
        return mav;
    }


}
