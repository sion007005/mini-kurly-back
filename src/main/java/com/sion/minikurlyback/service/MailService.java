package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.MailInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void send(MailInfoDto mailInfoDto) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailInfoDto.getAddress());
        mail.setSubject(mailInfoDto.getTitle());
        mail.setText(mailInfoDto.getMessage());

        javaMailSender.send(mail);
    }
}
