package com.lucifer.e_com_app.controller;

import com.lucifer.e_com_app.modules.Product;
import com.lucifer.e_com_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable int pid){
        Product product = service.getProductById(pid);
        if (product!=null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        // (@RequestBody Product product) -> can be used if only json is to be saved as object

        try {
            Product savedProduct = service.addProduct(product, imageFile);
            return  new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{pid}/image")
    public ResponseEntity<?> getImageByProductId(@PathVariable int pid){
        Product product = service.getProductById(pid);
        if (product!=null) {
            byte[] imageData = product.getImageData();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(product.getImageType()))
                    .body(imageData);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
