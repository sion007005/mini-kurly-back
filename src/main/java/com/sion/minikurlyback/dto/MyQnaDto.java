package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyQnaDto { // 1:1 문의 내용을 받아오는 dto
    private String title;
    private String content;
}
