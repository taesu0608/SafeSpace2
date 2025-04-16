package com.example.demo.controller;

import com.example.demo.dto.SubscribeInformationDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import com.example.demo.entity.SubscribeInformation;
import com.example.demo.entity.WorkplaceInformation;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/subscribe_info")
@RequiredArgsConstructor
public class SubscribeInformationController {
    private final SubscribeInformationService subscribeInformationService;
    private final ProductService productService;
    private final ProviderService providerService;
    private final WorkplaceInformationService workplaceInformationService;

    //모든 구독 정보 조회
    @GetMapping("/getAll/")
    public List<SubscribeInformation> getAllSubscribeInformation() {
        return subscribeInformationService.getAllSubscribeInformation();
    }

    //구독 정보 추가
    @PostMapping("/add/")
    public Boolean addSubscribeInformation(@RequestParam("uid") String uid,
                                           @RequestParam("productId") String productId) {
        
        Product product = productService.getProductById(productId);
        Provider provider = product.getProvider();
        WorkplaceInformation workplaceInformation = workplaceInformationService.getWorkplaceInformation(uid);

        if(!workplaceInformationService.exsitWorkplaceInformation(uid)) return false; //회원이 아닐 경우 false
        // end = 현재 서버시간 + 상품 month
        LocalDate now = InitService.ServerTime().toLocalDate();
        LocalDate end = now.plusMonths(product.getMonth());
        
        //TODO: 구독일과 결제일을 다르게
        subscribeInformationService.saveSubscribeInformation(
                SubscribeInformation.builder().uid(uid)
                    .uid(uid)
                    .startDate(now)
                    .endDate(end)
                    .paymentDate(now)
                    .workplaceInformation(workplaceInformation)
                    .nextPaymentDate(end)
                    .product(product)
                    .provider(provider)
                    .build()
                );
        return true;
    }

    //id 조회
    @GetMapping("/find/")
    public SubscribeInformationDto getSubscribeInformationById(@RequestParam("id") Integer id) {
        return new SubscribeInformationDto().toDTO(
                subscribeInformationService.getSubscribeInformationById(id)
        );
    }

    //uid 조회
    @PostMapping("/findByUid/")
    public List<SubscribeInformationDto> getSubscribeInformationByUid(@RequestParam("uid") String uid) {
        ArrayList<SubscribeInformation> subscribeInformations
                = (ArrayList<SubscribeInformation>) subscribeInformationService.getSubscribeInformationByUid(uid);

        ArrayList<SubscribeInformationDto> dtos = new ArrayList<>();
        for (SubscribeInformation subscribeInformation : subscribeInformations) {
            dtos.add(new SubscribeInformationDto().toDTO(subscribeInformation));
        }

        return dtos;
    }
}
