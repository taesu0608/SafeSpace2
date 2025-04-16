package com.example.demo.controller;

import com.example.demo.dto.WorkplaceInformationDto;
import com.example.demo.entity.RegisterIp;
import com.example.demo.entity.WorkplaceInformation;
import com.example.demo.model.response.ListResult.CommonResult;
import com.example.demo.service.RegisterIpService;
import com.example.demo.service.WorkplaceInformationService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/workplace")
@RequiredArgsConstructor
public class WorkplaceInformationController {
    private final WorkplaceInformationService workplaceInformationService;
    private final ServletRequest httpServletRequest;
    private final RegisterIpService registerIpService;

    @PostMapping("/find/") //사용자 조회
    public WorkplaceInformationDto getdWorkplaceInformationByUid(@RequestParam("uid") String uid) {
        return new WorkplaceInformationDto().toDTO(
                workplaceInformationService.getWorkplaceInformation(uid)
        );
    }

    @PostMapping("/exist/")
    public Boolean existWorkplaceInformationByUid(@RequestParam("uid") String uid) {
        return workplaceInformationService.exsitWorkplaceInformation(uid);
    }

    @PostMapping("/join/") //회원가입
    public CommonResult joinWorkplace(@RequestBody WorkplaceInformationDto workplaceInformationDTO) {
        String uid = workplaceInformationDTO.getUid();
        System.out.println(uid);

        if(workplaceInformationService.exsitWorkplaceInformation(uid)) return CommonResult.builder()
                .code(-1)
                .msg("이미 존재하는 회원입니다!")
                .success(false)
                .build(); //이미 회원이라면

        if(registerIpService.getRegisterIpByIp(workplaceInformationDTO.getWorkspaceIp()) != null)
            return CommonResult.builder()
                    .code(-2)
                    .msg("이미 등록된 IP입니다!")
                    .build();

        WorkplaceInformation workplaceInformation = WorkplaceInformation.toEntity(workplaceInformationDTO);

        workplaceInformationService.saveWorkplaceInformation(workplaceInformation);
        //IP 등록
        registerIpService.saveRegisterIp(
                RegisterIp.builder()
                        .workplaceInformation(workplaceInformation)
                        .ip(workplaceInformation.getWorkspaceIp())
                        .build()
        );
        return CommonResult.builder()
                .code(0)
                .msg("회원가입 성공했습니다.")
                .success(true)
                .build();
    }

    @GetMapping("/ip_check/")
    public String ipCheck(){
        return httpServletRequest.getRemoteAddr();
    }
}
