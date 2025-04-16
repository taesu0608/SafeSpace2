package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProviderCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Category category;

    @Builder
    public ProviderCategory(Provider provider, Category category) {
        this.provider = provider;
        this.category = category;
    }

    public static ProviderCategory toEntity(
            Provider provider, Category category) {

        return ProviderCategory.builder()
                .provider(provider)
                .category(category)
                .build();
    }
}
