package com.example.demo.repository;

import com.example.demo.entity.WorkplaceInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkplaceInformationRepo extends JpaRepository<WorkplaceInformation, String> {
    Optional<WorkplaceInformation> findById(String id);
}
