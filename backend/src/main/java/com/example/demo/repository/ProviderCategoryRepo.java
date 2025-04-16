package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import com.example.demo.entity.ProviderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderCategoryRepo extends JpaRepository<ProviderCategory,Integer> {
    List<ProviderCategory> findByProvider(Provider provider);
    List<ProviderCategory> findByCategory(Category category);
}
