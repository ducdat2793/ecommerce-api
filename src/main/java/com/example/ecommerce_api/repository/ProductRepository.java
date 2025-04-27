package com.example.ecommerce_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce_api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByNameContainingIgnoreCase(String name);
    
}
