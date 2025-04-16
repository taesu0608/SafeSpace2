package com.example.demo.repository;

import com.example.demo.entity.SubscribeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeInformationRepo extends JpaRepository<SubscribeInformation, Integer> {
    List<SubscribeInformation> findByUid(String uid);
}
