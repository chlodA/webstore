package com.example.SpringApp.controller;


import com.example.SpringApp.model.Product;
import com.example.SpringApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> productList = productService.getProducts();
        model.addAttribute("productList", productList);
        return "products_list";
    }

    @GetMapping("/products/create")
    public String showProducts(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping(value="/saveProduct")
    public String addProd(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        //Product prod = productServiceImpl.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        Product product = productService.getProductById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable (value = "id") long id) {
        this.productService.deleteProductById(id);
        return "redirect:/products";
    }


/*    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get product from the service
        Product product = productService.getProductById(id);

        // set product as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        return "update";
    }

    @GetMapping("/deleteEProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {

        // call delete product method
        this.productService.deleteProductById(id);
        return "redirect:/";
    }*/


}
