package com.example.demo.service;

import com.example.demo.entity.SubscribeInformation;
import com.example.demo.repository.SubscribeInformationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscribeInformationService {
    private final SubscribeInformationRepo subscribeInformationRepo;

    //모든 구독 상황 조회
    public List<SubscribeInformation> getAllSubscribeInformation() {
        return subscribeInformationRepo.findAll();
    }

    //상품 검색
    public SubscribeInformation getSubscribeInformationById(Integer id) {
        Optional<SubscribeInformation> subscribeInformation =  subscribeInformationRepo.findById(id);
        if(subscribeInformation.isPresent()) {
            return subscribeInformation.get();
        }else
            return null;
    }

    //uid 조회
    public List<SubscribeInformation> getSubscribeInformationByUid(String uid) {
        return subscribeInformationRepo.findByUid(uid);
    }

    //저장
    public void saveSubscribeInformation(SubscribeInformation subscribeInformation) {
        subscribeInformationRepo.save(subscribeInformation);
    }

    //구독 시작일
    //구독 종료일
    public void saveSubscriveInformationDate(Integer id) {
        InitService.ServerTime();
    }


    //결제 시작일
    //결제 종료일
}
