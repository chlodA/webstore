package com.example.SpringApp.controller;


import com.example.SpringApp.model.Product;
import com.example.SpringApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8080/product?id=1
    @GetMapping("/product")
    public String findByProductId(@RequestParam("id") String
                                         productId, Model model) {
        model.addAttribute("product",productService.findByItemNumber(productId));
        return "product";
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
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
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
        Product product = productService.getProductById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));;
        model.addAttribute("product", product);
        return "update_product";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update_product";
        }

        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable (value = "id") long id) {
            this.productService.deleteProductById(id);
            return "redirect:/products";

    }

}
