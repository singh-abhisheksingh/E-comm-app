package com.lucifer.e_com_app.service;

import com.lucifer.e_com_app.modules.Product;
import com.lucifer.e_com_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int pid) {
        return repository.findById(pid).orElse(new Product());
    }
}
