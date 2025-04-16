package com.example.demo.model.response.ListResult;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult{
    // List 형태로 여러 데이터를 받음
    // CommonResult를 통해 API 요청결과도 같이 출력

    private List<T> list;

    ListResult(boolean success, int code, String msg) {
        super(success, code, msg);
    }
}
