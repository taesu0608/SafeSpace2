package com.example.demo.entity;

import com.example.demo.dto.SubscribeInformationDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EnableJpaAuditing
public class SubscribeInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String uid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextPaymentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Builder
    public SubscribeInformation(String uid, String name, LocalDate endDate,
                                LocalDate nextPaymentDate,
                                LocalDate paymentDate, LocalDate startDate,
                                WorkplaceInformation workplaceInformation,
                                Product product,
                                Provider provider){
        this.uid = uid;
        this.endDate = endDate;
        this.nextPaymentDate = nextPaymentDate;
        this.paymentDate = paymentDate;
        this.startDate = startDate;
        this.workplaceInformation = workplaceInformation;
        this.product = product;
        this.provider = provider;
    }

    @ManyToOne
    private WorkplaceInformation workplaceInformation;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Product product;


    public static SubscribeInformation toEntity(SubscribeInformationDto dto, Provider provider, Product product){
        return SubscribeInformation.builder()
                .uid(dto.getUid())
                .endDate(dto.getEndDate())
                .nextPaymentDate(dto.getNextPaymentDate())
                .paymentDate(dto.getPaymentDate())
                .startDate(dto.getStartDate())
                .product(product)
                .provider(provider)
                .build();
    }
}
