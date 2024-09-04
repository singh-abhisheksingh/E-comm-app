package com.lucifer.e_com_app.repository;

import com.lucifer.e_com_app.modules.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
