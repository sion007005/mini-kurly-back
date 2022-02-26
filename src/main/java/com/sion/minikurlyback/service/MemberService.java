package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.MemberDto;
import com.sion.minikurlyback.entity.Address;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.enums.Authority;
import com.sion.minikurlyback.jwt.SecurityUtil;
import com.sion.minikurlyback.repository.AddressRepository;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.utils.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Transactional
    public Long join(MemberDto memberDto) {
        Member oneByMemberId = memberRepository.findOneByMemberId(memberDto.getMemberId());

        if (Objects.nonNull(oneByMemberId)) {
            throw new DuplicateMemberException("사용중인 아이디입니다.");
        }

        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .gender(memberDto.getGender())
                .birth(memberDto.getBirth())
                .authority(Authority.ROLE_USER)
                .build();

        memberRepository.save(member);

        Address address = Address.builder()
                .memberIdx(member.getIdx())
                .addressBasic(memberDto.getAddressBasic())
                .addressDetail(memberDto.getAddressDetail())
                .zipCode(memberDto.getZipCode())
                .mainAddress(memberDto.getMainAddress())
                .build();
        addressRepository.save(address);

        return member.getIdx();
    }

    @Transactional(readOnly = true)
    public MemberDto findOneByMemberId(String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        if (Objects.isNull(member)) {
            throw new RuntimeException("유저 정보가 없습니다.");
        }

        MemberDto memberDto = MemberDto.from(member);

        return memberDto;
    }

    @Transactional(readOnly = true)
    public MemberDto getMyInfo() {
        Member member = memberRepository.findOneByMemberId(SecurityUtil.getCurrentMemberId());
        MemberDto memberDto = MemberDto.from(member);

        return memberDto;
    }
}
