package com.example.demo.entity;

import com.example.demo.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(nullable = false, length = 150)
    private String productId;

    @Column(length = 100)
    private String productName;

    @Column(length = 255)
    private String imgPath;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private Integer capacity;

    @Column(length = 100)
    private Integer month;

    @Column(length = 100)
    private Integer price;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SubscribeInformation> subscribeInformation;

    @Builder
    public Product(String product_id,String product_name, String imgPath, Category category, String description, Integer capacity, Integer month, Integer price, Provider provider) {
        this.productId = product_id;
        this.productName = product_name;
        this.imgPath = imgPath;
        this.category = category;
        this.description = description;
        this.capacity = capacity;
        this.month = month;
        this.price = price;
        this.provider = provider;
    }

    public static Product toEntity(ProductDto dto, Provider provider) {
        return Product.builder()
                .product_id(dto.getProductId())
                .product_name(dto.getProductName())
                .imgPath(dto.getImgPath())
                .category(Category.toEntity(dto.getCategory()))
                .description(dto.getDescription())
                .capacity(dto.getCapacity())
                .month(dto.getMonth())
                .price(dto.getPrice())
                .provider(provider)
                .build();
    }
}
