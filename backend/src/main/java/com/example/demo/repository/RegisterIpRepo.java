package com.example.demo.repository;

import com.example.demo.entity.RegisterIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterIpRepo extends JpaRepository<RegisterIp,String> {
    Optional<RegisterIp> findById(String id);
}
