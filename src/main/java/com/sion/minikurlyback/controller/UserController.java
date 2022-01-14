package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/join")
    public String create(UserDto user) {
        // TODO
        return "test";
    }

}
