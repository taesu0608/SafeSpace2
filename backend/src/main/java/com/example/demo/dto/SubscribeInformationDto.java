package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.SubscribeInformation;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeInformationDto {
    private Integer id;
    private String uid;
    private ProviderDto provider;
    private ProductDto product;
    private LocalDate endDate;
    private LocalDate nextPaymentDate;
    private LocalDate paymentDate;
    private LocalDate startDate;


    public static SubscribeInformationDto toDTO(SubscribeInformation entity) {
        return SubscribeInformationDto.builder()
                .id(entity.getId())
                .uid(entity.getUid())
                .provider(ProviderDto.toDTO(entity.getProvider()))
                .product(ProductDto.toDTO(entity.getProduct()))
                .endDate(entity.getEndDate())
                .nextPaymentDate(entity.getNextPaymentDate())
                .paymentDate(entity.getPaymentDate())
                .startDate(entity.getStartDate())
                .build();
    }
}
