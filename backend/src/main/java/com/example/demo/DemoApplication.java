package com.example.demo;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProviderDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProviderRepo;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProviderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//로그인 (security 배제)
@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



    //localhost:8080를 호출하면 helloWorld를 찍어주기 위한 함수
    @RequestMapping("/")
    public String helloWorld() {
        System.out.println("Hello World");
        return "hello";
    }
}
