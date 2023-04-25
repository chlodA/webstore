package com.example.SpringApp.controller;


import com.example.SpringApp.exceptions.NoProductsFoundUnderCategoryException;
import com.example.SpringApp.exceptions.ProductNotFoundException;
import com.example.SpringApp.model.Product;
import com.example.SpringApp.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            throw new ProductNotFoundException(id);
        } else {
            model.addAttribute("product", product);
        }
        return "product";
    }

    @GetMapping("/products/{category}")
    public String findByCategory(@PathVariable("category") String category, Model model){
        List<Product> products = productService.findByCategory(category);
        // If we didn't find a product matching that category throw an exception
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException(category);
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



/*TO DO add multiple filters to list products
* http://localhost:8080/products/Tablet/price;low=200;high=400?brand="Google"*/
   /* @RequestMapping("/products/{category}/{params}")
    public String filterProducts(@PathVariable("category") String productCategory,@MatrixVariable(pathVar="params") Map <String,
            List<String>> filterParams ,Model model){

        // Request a product with a particular category  and add it to the model
        model.addAttribute("productsList",productService.getProductsByManuAndBrand(productCategory, filterParams));

        // Let the View Resolver know what view page to use
        return "products_list";
    }*/

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
            this.productService.deleteProductById(id);
            return "redirect:/products";

    }

}
