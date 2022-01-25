package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.MemberDto;
import com.sion.minikurlyback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Long> join(@Valid MemberDto memberDto) {
        return ResponseEntity.ok(memberService.join(memberDto));
    }

    @GetMapping("/member/info")
    public ResponseEntity<MemberDto> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/member/memberId")
    public ResponseEntity<MemberDto> findOneByMemberId(@PathVariable String memberId) {
        return ResponseEntity.ok(memberService.findOneByMemberId(memberId));
    }
}
