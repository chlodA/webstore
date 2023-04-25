package com.example.SpringApp.controller;

import com.example.SpringApp.exceptions.NoProductsFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoProductsFoundException.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    NoProductsFoundException exception) {
        ModelAndView mav = new ModelAndView();
     /*   mav.addObject("invalidCategoryName",
                exception.findByCategory());*/
        mav.addObject("exception", exception);
        mav.addObject("url",
                req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("error_category_name");
        return mav;
    }




}
