package com.sion.minikurlyback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.enums.Gender;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private String birth;

    public MemberDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.gender = member.getGender();
        this.birth = member.getBirth();
    }

    public static MemberDto from(Member member) {
        if(member == null) return null;

        return MemberDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .gender(member.getGender())
                .birth(member.getBirth())
                .build();
    }
}
