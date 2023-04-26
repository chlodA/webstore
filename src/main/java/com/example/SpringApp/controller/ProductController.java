package com.example.SpringApp.controller;


import com.example.SpringApp.exceptions.ProductAlreadyExistsException;
import com.example.SpringApp.exceptions.ProductNotFoundException;
import com.example.SpringApp.model.Product;
import com.example.SpringApp.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Sample URL: http://localhost:8080/product?id=1
    @GetMapping("/product")
    public String findByProductId(@RequestParam("id") long id, Model model) {
        Product product = productService.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException();
        } else {
            model.addAttribute("product", product);
        }
        return "product";
    }

    @GetMapping("/products/category/{category}")
    public String findByCategory(@PathVariable("category") String category, Model model){
        List<Product> products = productService.findByCategory(category);
        if (products == null || products.isEmpty()) {
            throw new ProductNotFoundException();
        }
        model.addAttribute("productList", products);
        return "products_list";
    }

    @GetMapping("/products/name/{name}")
    public String findByProductName(@PathVariable("name") String productName, Model model){
        List<Product> products = productService.findByProductName(productName);
        if (products == null || products.isEmpty()) {
            throw new ProductNotFoundException();
        }
        model.addAttribute("productList", products);
        return "products_list";
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
        List<Product> savedProduct = productService.findByProductName(product.getName());
        if(!savedProduct.isEmpty()){
            throw new ProductAlreadyExistsException();
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
        Product product = productService.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException();
        } else {
            model.addAttribute("product", product);
        }
        return "update_product";
    }
  /*TODO
  *  add error handling if name changed to one that already exists*/
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

    // Allows a search by brands and categories
    // Sample URL: http://localhost:8080//products/filter/params;brands=Google;category=Tablet
    @RequestMapping("/products/filter/{params}")
    public String getProductsByFilter(@MatrixVariable(pathVar="params") Map <String, String> filterParams,Model model){

        String manufacturer = (String)filterParams.get("brands");
        String category = (String)filterParams.get("category");

        model.addAttribute("productList", productService.getProductsByFilter(category,manufacturer));

        return "products_list";
    }


    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable (value = "id") long id) {
        Product product = productService.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException();
        } else {
            this.productService.deleteProductById(id);
        }
            return "redirect:/products";

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
       /* mav.addObject("invalidCategoryName",
                exception.findByCategory());*/
        mav.addObject("exception", exception);
        mav.addObject("url",
                req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("errors/error_product_not_found_404");
        return mav;
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    ProductAlreadyExistsException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url",
                req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("errors/error_product_already_exists_400");
        return mav;
    }




}
