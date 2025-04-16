package com.example.demo.dto;

import com.example.demo.entity.RegisterIp;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterIpDto {
    private String ip;
    private String uid;

    public static RegisterIpDto toDTO(RegisterIp entity) {
        return RegisterIpDto.builder()
                .ip(entity.getIp())
                .uid(entity.getUid())
                .build();
    }
}
