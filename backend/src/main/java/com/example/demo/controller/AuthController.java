package com.example.demo.controller;

import com.example.demo.entity.RegisterIp;
import com.example.demo.entity.SubscribeInformation;
import com.example.demo.entity.WorkplaceInformation;
import com.example.demo.model.response.ListResult.CommonResult;
import com.example.demo.service.RegisterIpService;
import com.example.demo.service.SubscribeInformationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.h;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterIpService registerIpService;
    private final HttpServletRequest httpServletRequest;
    private final SubscribeInformationService subscribeInformationService;

    @GetMapping("/")
    public CommonResult auth(
            @RequestParam("providerId") String providerId,
            @RequestParam("categoryId") String categoryId
    ){
        //파라미터 확인
        if(providerId == null || categoryId == null)
            return CommonResult.builder().success(false)
                    .code(-1)
                    .msg("Empty Parameter").build();

        //ip 확인
        RegisterIp registerIp = registerIpService.getRegisterIpByIp(httpServletRequest.getRemoteAddr());
        if(registerIp == null)
            return CommonResult.builder().success(false)
                    .code(-2)
                    .msg("Unauthenticated IP").build();

        //uid로 구독 정보를 가져옴
        List<SubscribeInformation> subscribeInformations
                = subscribeInformationService.getSubscribeInformationByUid(registerIp.getUid());

        //요청한 상품이 있는지 확인
        boolean auth = false;
        for(SubscribeInformation subscribeInformation : subscribeInformations)
            if(subscribeInformation.getProvider().getProviderId().equals(providerId)
            && subscribeInformation.getProduct().getCategory().getId().equals(categoryId)){
                auth = true;
                break;
            }

        if(auth == false)
            return CommonResult.builder().success(false)
                    .code(-3)
                    .msg("Unauthorized Product").build();

        return CommonResult.builder().success(true)
                .code(0)
                .msg("Auth Success").build();
    }



}
