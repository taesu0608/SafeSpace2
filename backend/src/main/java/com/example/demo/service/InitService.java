package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProviderCategoryDto;
import com.example.demo.dto.ProviderDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitService implements CommandLineRunner {

    private final ProductService productService;
    private final ProviderService providerService;
    private final CategoryService categoryService;
    private final ProviderCategoryService providerCategoryService;
    private final WorkplaceInformationService workplaceInformationService;
    private final SubscribeInformationService subscribeInformationService;
    private final ProductRepo productRepo;
    private final ProviderRepo providerRepo;
    private final CategoryRepo categoryRepo;
    private final ProviderCategoryRepo providerCategoryRepo;
    private final WorkplaceInformationRepo workplaceInformationRepo;

    @Override
    public void run(String... args) throws Exception {
        if(productRepo.count() == 0) InitDB(); //중복 방지
        log.info("memberService1 = " + productRepo);

    }

    public void InitDB() {
        ArrayList<Provider> providers = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        for (CategoryDto dto : categoryService.categoryInit()) {
            categoryService.saveCategory(Category.toEntity(dto));
        }


        Provider providerEntity;
        ProviderCategory pc;
        for (ProviderDto pv : providerService.providerInit()) {
            providerEntity = Provider.toEntity(pv);
            for (ProductDto p : productService.productInit()) {
                log.info(p.getProviderId());
                log.info(pv.getProviderId());
                if (p.getProviderId().equals(pv.getProviderId())) products.add(Product.toEntity(p, providerEntity));
            }
            providers.add(providerEntity);
        }
        providerRepo.saveAll(providers);
        productRepo.saveAll(products);

        //provider_category matching
        for (ProviderCategoryDto dto : providerCategoryService.providerCategoryInit()) {
            providerCategoryService.saveProviderCategory(
                    ProviderCategory.toEntity(
                            Provider.toEntity(dto.getProvider()),
                            Category.toEntity(dto.getCategory())
                    )
            );
        }
    }

    @SneakyThrows//상위로 예외던짐
    public static String resourceJsonToString(String path) {
        String str = "";
        String sb = "";
        ClassPathResource resource = null;
        InputStreamReader reader = null;

        //static폴더의 json파일을 string으로 저장
        try {
            resource = new ClassPathResource("static/" + path);
            reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            while ((str = br.readLine()) != null) {
                sb += str + "\n";
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return sb;
    }
    
    //서버 시간 반환
    public static LocalDateTime ServerTime(){
        return LocalDateTime.now();
    }
}
/* JSON(Object, Array) -> Object 변환 로직
        String str = "";
        String sb = "";
        ClassPathResource resource = null;
        InputStreamReader reader = null;

        //static폴더의 json파일을 string으로 저장
        try {
            resource = new ClassPathResource("static/product.json");
            reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            while ((str = br.readLine()) != null) {
                sb += str + "\n";
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        //String(json)을 Object로 변환
        ObjectMapper objectMapper = new ObjectMapper();

        //Json Object일 경우 사용
        /*ProductDto product = objectMapper.readValue(sb, ProductDto.class);

        //Json Array일 경우 사용
        ArrayList<ProductDto> products = objectMapper.readValue(sb,new TypeReference<ArrayList<ProductDto>>(){});
        */
