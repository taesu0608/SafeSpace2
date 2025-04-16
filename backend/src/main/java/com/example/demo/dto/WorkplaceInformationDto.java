package com.example.demo.dto;

import com.example.demo.entity.WorkplaceInformation;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceInformationDto {
    private String uid;
    private String workspaceIp;
    private String dancPin;
    private String workspaceName;
    private String ownerName;
    private String address;
    private String businessType;
    private String workspacePhone;
    private LocalDate joinDate;

    public static WorkplaceInformationDto toDTO(WorkplaceInformation entity) {
        return WorkplaceInformationDto.builder()
                .uid(entity.getUid())
                .workspaceIp(entity.getWorkspaceIp())
                .dancPin(entity.getDancPin())
                .workspaceName(entity.getWorkspaceName())
                .ownerName(entity.getWorkspaceName())
                .address(entity.getAddress())
                .businessType(entity.getBusinessType())
                .workspacePhone(entity.getWorkspacePhone())
                .joinDate(entity.getJoinDate())
                .build();
    }
}
