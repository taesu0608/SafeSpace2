package com.example.demo.controller;

import com.example.demo.entity.RegisterIp;
import com.example.demo.entity.WorkplaceInformation;
import com.example.demo.service.RegisterIpService;
import com.example.demo.service.WorkplaceInformationService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/registerIp")
@RequiredArgsConstructor
public class RegisterIpController {
    private final RegisterIpService registerIpService;
    private final WorkplaceInformationService workplaceInformationService;
    private final ServletRequest httpServletRequest;

    @GetMapping("/register/")
    public boolean registerIp(@RequestParam("uid") String uid) {
        WorkplaceInformation workplaceInformation = workplaceInformationService.getWorkplaceInformation(uid);
        if(workplaceInformation == null) return false; //등록된 사용자가 아닌 경우

        String ip = httpServletRequest.getRemoteAddr();
        if(registerIpService.getRegisterIpByIp(ip) != null) return false; //이미 IP가 등록되어 있는 경우

        registerIpService.saveRegisterIp(
                RegisterIp.builder()
                        .ip(ip)
                        .uid(uid)
                        .workplaceInformation(workplaceInformation)
                        .build()
        );

        return true;
    }
}
