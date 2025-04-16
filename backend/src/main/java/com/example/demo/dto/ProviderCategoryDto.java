package com.example.demo.dto;

import com.example.demo.entity.ProviderCategory;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderCategoryDto {
    private Integer id;
    //id로 하면 너무 많은 통신을 해야함으로 dto 객체 통재로 넘김
    private ProviderDto provider; //dto 객체로 변환해 저장
    private CategoryDto category;

    public static ProviderCategoryDto toDto(ProviderCategory entity) {
        return ProviderCategoryDto.builder()
                .id(entity.getId())
                .provider(ProviderDto.toDTO(entity.getProvider()))
                .category(CategoryDto.toDto(entity.getCategory()))
                .build();
    }
}
