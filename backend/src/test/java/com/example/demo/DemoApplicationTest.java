package com.example.demo;

import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestService;
import com.example.demo.entity.TestE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTest {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestService testService;

    @Test
    void test() {
        //Test할 Entity 입력
        TestE testEntity = TestE.builder()
                .testContents("4")
                .build();

        
        //Jpa save()실행
        testRepository.save(testEntity);
        //콘솔창에표시
        List<TestE> testList = testRepository.findAll();
    }
}