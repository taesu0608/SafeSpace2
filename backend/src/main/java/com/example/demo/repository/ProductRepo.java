package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    Optional<Product> findById(String id);
    List<Product> findByProvider(Provider provider);
}
