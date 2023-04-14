package com.example.SpringApp.controller;


import com.example.SpringApp.model.Product;
import com.example.SpringApp.service.ProductService;
import com.example.SpringApp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
//@RequestMapping("/add" )
public class ProductController {

@Autowired
    private ProductService productService;

   public ProductController(ProductService productService){
        this.productService = productService;
    }


    //@GetMapping("/")
    @RequestMapping(value = "products/add", method = RequestMethod.GET)
    public String showProducts(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Product> productList = productService.getProducts();
        model.addAttribute("productList", productList);

        return "add";
    }

    @RequestMapping(value="/products/add", method = RequestMethod.POST)
    public String addProd(@ModelAttribute("product") Product product){
       productService.addProduct(product);
            //Product prod = productServiceImpl.addProduct(product);

        return "redirect:/items";
    }

/*
    @GetMapping(value = "/items")
    public String showItems(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Product> productList = productService.getProducts();
        model.addAttribute("productList", productList);

        return "items";
    }

 @RequestMapping(value="/items/{description}", method = RequestMethod.GET)
    public String getProductsByDescription(
                                        @PathVariable("description") String description, Model model) {

         model.addAttribute("prod", productService.getProductsByDescription(description));
        return "items";

    }


    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String
                                         productId, Model model) {
        model.addAttribute("product",productService.getProductById(productId));
        return "product";
    }
*/

}
