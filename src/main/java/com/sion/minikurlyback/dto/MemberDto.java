package com.sion.minikurlyback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.enums.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    @NotBlank
    @Length(min = 6, max = 10)
    private String memberId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "패스워드는 대문자, 소문자, 특수문자를 적어도 하나씩 포함하며 최소 8자리이상 최대 20자리까지 가능합니다.")
    private String password;

    @NotBlank
    @Length(min = 2)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
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
