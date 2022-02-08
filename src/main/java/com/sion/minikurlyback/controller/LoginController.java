package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.LoginDto;
import com.sion.minikurlyback.dto.TokenDto;
import com.sion.minikurlyback.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(loginService.login(loginDto));
    }


//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }
}
