package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDto {
    private String providerId;
    private String name;
    private String imgPath;

    public static ProviderDto toDTO(Provider entity) {
        return ProviderDto.builder()
                .providerId(entity.getProviderId())
                .name(entity.getName())
                .imgPath(entity.getImgPath())
                .build();
    }
}