package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import com.example.demo.repository.ProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.*;
import java.io.InputStreamReader;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepo productRepo;

    @SneakyThrows//상위로 예외던짐
    public ArrayList<ProductDto> productInit() {
        log.info("memberService1 = " + productRepo);
        //String(json)을 Object로 변환
        ObjectMapper objectMapper = new ObjectMapper();

        //Json Array일 경우 사용
        ArrayList dtos = objectMapper.readValue(InitService.resourceJsonToString("product.json"),new TypeReference<ArrayList<ProductDto>>(){});
        return dtos;
    }

    //개별 상품 저장
    public void saveProduct(Product product) {productRepo.save(product);}
    
    //모든 상품 저장
    public void saveAllProduct(Product product) {
        productRepo.save(product);
    }

    //모든 상품 조회
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    //개별 상품 조회
    public Product getProductById(String productId) {
        Optional<Product> product =  productRepo.findById(productId);
        if(product.isPresent())
        {
            return product.get();
        }else
            return null;
    }

    //providerId로 product 조회
    public List<Product> getProductByProvider(Provider provider){
        return productRepo.findByProvider(provider);
    }
}

