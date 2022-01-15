package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.UserDto;
import com.sion.minikurlyback.repository.UserRepository;
import com.sion.minikurlyback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user/join")
    public Long create(UserDto userDto) {
        Long userIdx = userService.create(userDto);
        return userIdx;
    }

    @GetMapping("/user/{idx}")
    public UserDto findOne(@PathVariable Long idx) {
        UserDto userDto = userService.findOneByIdx(idx);
        return userDto;
    }
}
