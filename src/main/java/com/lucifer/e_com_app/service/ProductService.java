package com.lucifer.e_com_app.service;

import com.lucifer.e_com_app.modules.Product;
import com.lucifer.e_com_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int pid) {
        return repository.findById(pid).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repository.save(product);
    }

    public Product updateProduct(int pid, Product product, MultipartFile imageFile) throws IOException {
        if (repository.findById(pid).orElse(null)!=null){
            product.setImageData(imageFile.getBytes());
            product.setImageType(imageFile.getContentType());
            product.setImageName(imageFile.getOriginalFilename());
            repository.save(product);
            return product;
        }
        else return null;
    }

    public void deleteProductById(int pid) {
        repository.deleteById(pid);    }
}
