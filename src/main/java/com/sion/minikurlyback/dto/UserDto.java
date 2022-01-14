package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.entity.Gender;

public class UserDto {
    private String user_id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private String birth;
    private String recommendedBy;
    private Boolean agreedAllTerms;
}
