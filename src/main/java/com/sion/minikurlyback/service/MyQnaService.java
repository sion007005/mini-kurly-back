package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.MyQnaDto;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.entity.MyQna;
import com.sion.minikurlyback.enums.QnaStatus;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.repository.MyQnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyQnaService {
    private final MyQnaRepository myQnaRepository;
    private final MemberRepository memberRepository;

    public Long register(MyQnaDto myQnaDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        MyQna myQna = MyQna.builder()
            .member(member)
            .title(myQnaDto.getTitle())
            .content(myQnaDto.getContent())
            .qnaStatus(QnaStatus.WAITING)
            .build();

        myQnaRepository.save(myQna);

        return myQna.getId();
    }
}
