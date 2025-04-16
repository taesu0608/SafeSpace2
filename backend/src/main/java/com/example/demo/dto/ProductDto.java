package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productId;
    private String productName;
    private String providerId;
    private String imgPath;
    private CategoryDto category; //TODO : 확인 필요
    private String description;
    private Integer capacity;
    private Integer month;
    private Integer price;

    public static ProductDto toDTO(Product entity) {
        return ProductDto.builder()
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .imgPath(entity.getImgPath())
                .category(CategoryDto.toDto(entity.getCategory()))
                .description(entity.getDescription())
                .capacity(entity.getCapacity())
                .month(entity.getMonth())
                .price(entity.getPrice())
                .providerId(entity.getProvider().getProviderId())
                .build();
    }
}
