
package com.example.demo.controller;

import com.example.demo.service.ProviderService;
import com.example.demo.entity.Provider;
import com.example.demo.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //JSON 형태로 반환하는 컨트롤러
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {
    ProviderService providerService;

    //Id로 Provider 조회
    @GetMapping("/find/{providerId}")
    public Provider getProviderById(@PathVariable String providerId) {
        return providerService.getProviderById(providerId);
    }

    //모든 Provider 조회
    @GetMapping("/findAll/")
    public List<ProviderDto> getProvider() {
        ArrayList<ProviderDto> dtos = new ArrayList<>();
        for (Provider p : providerService.getAllProviders()) {
            dtos.add(new ProviderDto().toDTO(p));

        }
        return dtos;
    }
}
