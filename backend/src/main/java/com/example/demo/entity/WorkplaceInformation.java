package com.example.demo.entity;

import com.example.demo.dto.WorkplaceInformationDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EnableJpaAuditing
public class WorkplaceInformation {
    @Id
    @Column(length = 150)
    private String uid;

    @Column(length = 255)
    private String workspaceIp;

    @Column(length = 255)
    private String dancPin;

    @Column(length = 255)
    private String workspaceName;

    @Column(length = 255)
    private String ownerName;

    @Column(length = 255)
    private String address;

    @Column(length = 255)
    private String businessType;

    @Column(length = 255)
    private String workspacePhone;

    @Nullable //Todo: 예시로 삽입한 것 Nullable 삭제해야함
    private LocalDate joinDate;

    @Builder
    public WorkplaceInformation(String uid, String workspaceIp, String dancPin, String workspaceName, String ownerName,
                                String address, String businessType, String workspacePhone){
        this.uid = uid;
        this.workspaceIp = workspaceIp;
        this.dancPin = dancPin;
        this.workspaceName = workspaceName;
        this.ownerName = ownerName;
        this.address = address;
        this.businessType = businessType;
        this.workspacePhone = workspacePhone;
        this.joinDate = LocalDate.now();
        this.registerIps = new ArrayList<>();
        this.subscribeInformations = new ArrayList<>();
    }

    @OneToMany(mappedBy = "workplaceInformation", cascade = CascadeType.REMOVE)
    private List<RegisterIp> registerIps;

    @OneToMany(mappedBy = "workplaceInformation", cascade = CascadeType.REMOVE)
    private List<SubscribeInformation> subscribeInformations;

    public static WorkplaceInformation toEntity(WorkplaceInformationDto dto){
        if(dto == null) return null;

        return WorkplaceInformation.builder()
                .uid(dto.getUid())
                .workspaceIp(dto.getWorkspaceIp())
                .dancPin(dto.getDancPin())
                .workspaceName(dto.getWorkspaceName())
                .ownerName(dto.getOwnerName())
                .address(dto.getAddress())
                .businessType(dto.getBusinessType())
                .workspacePhone(dto.getWorkspacePhone())
                .build();
    }
}
