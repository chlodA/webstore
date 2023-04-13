package com.example.SpringApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class HelloController {


   @RequestMapping
   public String welcome(Model model) {
       //Add attributes to the model
       model.addAttribute("greeting", "Welcome to Web Store!");

       // requests to the welcome.html page.
       return "welcome";
   }

}