package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProviderDto;
import com.example.demo.entity.Provider;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProviderRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {

    @Autowired
    private ProviderRepo providerRepo;
    @Autowired
    private ProductRepo productRepo;

    @SneakyThrows//상위로 예외던짐
    public ArrayList<ProviderDto> providerInit() {
     ObjectMapper objectMapper = new ObjectMapper();
        //ProviderDto provider = objectMapper.readValue(sb, ProviderDto.class);

        //Json Array일 경우 사용
        ArrayList<ProviderDto> provider = objectMapper.readValue(
                InitService.resourceJsonToString("provider.json")
                ,new TypeReference<ArrayList<ProviderDto>>(){});
        return provider;
    }
    public void saveProvider(Provider provider) { providerRepo.save(provider);}

    //모든 컨텐츠 제공자 불러오기
    public List<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    public Provider getProviderById(String id) {
        Optional<Provider> provider = providerRepo.findById(id);
        return provider.isPresent() ? provider.get() : null;
    }

    // Todo 카테고리별 컨텐츠 제공자 불러오기
    public List<Provider> getCategory() {
        return providerRepo.findAll();
    }
}
