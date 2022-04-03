package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.MyQnaDto;
import com.sion.minikurlyback.service.MyQnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyQnaController {
    private final MyQnaService myQnaService;

    @PostMapping("/my-qna/add")
    public ResponseEntity create(@RequestBody MyQnaDto myQnaDto, @AuthenticationPrincipal String memberId) {
        Long savedId = myQnaService.register(myQnaDto, memberId);

        return ResponseEntity.ok().body("successfully saved : " + savedId);
    }
}
