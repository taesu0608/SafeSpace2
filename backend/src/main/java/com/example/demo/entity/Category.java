package com.example.demo.entity;

import com.example.demo.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @Column(length = 150)
    private String id;

    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProviderCategory> providerCategories;

    @Builder
    public Category(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
        products = new ArrayList<>();
        providerCategories = new ArrayList<>();
    }

    public static Category toEntity(CategoryDto dto){
        return Category.builder()
                .id(dto.getId())
                .categoryName(dto.getCategoryName())
                .build();
    }
}
