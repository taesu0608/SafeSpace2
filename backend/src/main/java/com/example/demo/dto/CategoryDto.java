package com.example.demo.dto;

import com.example.demo.entity.Category;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String id;
    private String categoryName;

    public static CategoryDto toDto(Category entity) {
        return CategoryDto.builder()
                .categoryName(entity.getCategoryName())
                .id(entity.getId())
                .build();
    }
}
