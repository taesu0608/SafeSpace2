package com.example.demo.entity;

import com.example.demo.dto.RegisterIpDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterIp {
    @Id
    @Column(length = 150)
    private String ip;

    @Column(length = 150)
    private String uid;

    @ManyToOne
    private WorkplaceInformation workplaceInformation;

    @Builder
    public RegisterIp(String ip, String uid, WorkplaceInformation workplaceInformation){
        this.ip = ip;
        this.uid = uid;
        this.workplaceInformation = workplaceInformation;
    }

    public static RegisterIp toEntity(RegisterIpDto dto, WorkplaceInformation workplaceInformation){
        return RegisterIp.builder()
                .ip(dto.getIp())
                .uid(dto.getUid())
                .workplaceInformation(workplaceInformation)
                .build();
    }
}
