package com.lucifer.e_com_app.controller;

import com.lucifer.e_com_app.modules.Product;
import com.lucifer.e_com_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String welcome(){
        return "Hello Abhishek";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/product/{pid}")
    public Product getProductById(@PathVariable int pid){
        return service.getProductById(pid);
    }
}
