package com.example.demo.dto;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    private Integer id;
    @NotNull
    private String userId;
    @NotNull
    private String testStr;
}