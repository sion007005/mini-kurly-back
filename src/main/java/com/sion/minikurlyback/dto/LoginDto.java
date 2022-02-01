package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String memberId;
    private String password;
    private String token;
}
