package com.example.demo.repository;

import com.example.demo.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepo extends JpaRepository<Provider,String> {
    Optional<Provider> findById(String id);
    List<Provider> findByName(String Name);
}
