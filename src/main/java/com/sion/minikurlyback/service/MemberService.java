package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.MemberDto;
import com.sion.minikurlyback.entity.Address;
import com.sion.minikurlyback.entity.AddressDetail;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.enums.Authority;
import com.sion.minikurlyback.exception.DuplicateMemberException;
import com.sion.minikurlyback.exception.IllegalRequestException;
import com.sion.minikurlyback.jwt.SecurityUtil;
import com.sion.minikurlyback.repository.AddressRepository;
import com.sion.minikurlyback.repository.MemberRepository;
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

        AddressDetail addressDetail = new AddressDetail(memberDto.getAddressBasic(), memberDto.getAddressDetail(), memberDto.getZipCode());
        Address address = Address.builder()
                .memberIdx(member.getIdx())
                .addressDetail(addressDetail)
                .mainAddress(memberDto.getMainAddress())
                .build();
        addressRepository.save(address);

        return member.getIdx();
    }

    @Transactional(readOnly = true)
    public MemberDto findOneByMemberId(String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        if (Objects.isNull(member)) {
            throw new IllegalRequestException("유저 정보가 없습니다.");
        }

        MemberDto memberDto = MemberDto.from(member);

        return memberDto;
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if (Objects.isNull(member)) {
            throw new IllegalRequestException("이메일 주소를 다시 확인해주세요.");
        }

        return member;
    }

    @Transactional(readOnly = true)
    public MemberDto getMyInfo() {
        Member member = memberRepository.findOneByMemberId(SecurityUtil.getCurrentMemberId());
        Address address = addressRepository.findByMemberIdx(member.getIdx()); // TODO 현재는 하나의 멤버가 하나의 주소만 갖는다. 추후 개선하기

        MemberDto memberDto = MemberDto.from(member);

        if (Objects.nonNull(address)) {
            memberDto.setAddressBasic(address.getAddressDetail().getAddressBasic());
            memberDto.setAddressDetail(address.getAddressDetail().getAddressDetail());
            memberDto.setZipCode(address.getAddressDetail().getZipCode());
            memberDto.setMainAddress(address.getMainAddress());
        }

        return memberDto;
    }

    @Transactional
    public void setTempPassword(String email, String tempPassword) {
        Member member = findByEmail(email);
        member.updatePassword(tempPassword);
    }

    @Transactional
    public void updatePassword(String memberId, String rawPassword) {
        Member member = memberRepository.findOneByMemberId(memberId);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.updatePassword(encodedPassword);
    }
}
