package com.example.demo.repository;

import com.example.demo.entity.TestE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository //<넣을 Entity, Id값>
public interface TestRepository extends JpaRepository<TestE, Integer> {
    Optional<TestE> findById(Integer id);
}