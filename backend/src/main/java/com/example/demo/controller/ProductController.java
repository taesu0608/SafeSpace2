package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/product")
@RequiredArgsConstructor
public class  ProductController {
    private final ProductService productService;
    private final ProviderService providerService;

    //모든 Product 조회
    @GetMapping("/findAll/")
    public List<ProductDto> getProduct() {
        ArrayList<ProductDto> dtos = new ArrayList<>();
        for (Product p : productService.getAllProduct()) {
            dtos.add(ProductDto.toDTO(p));
        }
        return dtos;
    }

    //개별 Product 조회
    @GetMapping("/find/")
    public ProductDto getProductById(@RequestParam("productId") String productId) {
        return  ProductDto.toDTO(
                productService.getProductById(productId)
        );
    }

    //ProviderId로 product 조회
    @GetMapping("/findProvider/")
    public List<ProductDto> getProductByProviderId(@RequestParam("providerId") String providerId){
        Provider provider = providerService.getProviderById(providerId);

        ArrayList<ProductDto> dtos = new ArrayList<>();
        for(Product p : productService.getProductByProvider(provider)){
            dtos.add(ProductDto.toDTO(p));
        }
        return dtos;
    }
}
