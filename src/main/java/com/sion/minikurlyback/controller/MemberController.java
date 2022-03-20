package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.MailInfoDto;
import com.sion.minikurlyback.dto.MemberDto;
import com.sion.minikurlyback.service.MailService;
import com.sion.minikurlyback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<Long> join(@Valid @RequestBody MemberDto memberDto) {
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

    @PostMapping("/temp-password")
    public ResponseEntity<String> setTemporaryPassword(@RequestParam String email) {
        String temporaryPassword = getTemporaryPassword();
        String encodedPassword = passwordEncoder.encode(temporaryPassword);
        memberService.setTempPassword(email, encodedPassword);

        MailInfoDto mailInfoDto = new MailInfoDto();
        mailInfoDto.setAddress(email);
        mailInfoDto.setTitle("임시 비밀번호 안내입니다.");
        mailInfoDto.setMessage("임시 생성된 비밀번호는 " + temporaryPassword + "입니다. 보안을 위해, 로그인 후 즉시 변경해주세요!");
        mailService.send(mailInfoDto);

        return ResponseEntity.ok("성공적으로 메일이 전송되었습니다.");
    }

    private String getTemporaryPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @PostMapping("/password/update")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal String memberId, String rawPassword) {
        memberService.updatePassword(memberId, rawPassword);

        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}
