package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.Authority;
import com.sion.minikurlyback.enums.Gender;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@Entity
@Table(
    name = "member",
    uniqueConstraints = {
            @UniqueConstraint(
                columnNames={"memberId","email", "phone"}
            )
    }
)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // PK

    @NotNull
    @Length(min = 6, max = 10)
    private String memberId; // 회원아이디

    @NotNull
    @Length(min = 6, max = 15)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "패스워드는 대문자, 소문자, 특수문자를 적어도 하나씩 포함하며 최소 8자리이상 최대 20자리까지 가능합니다.")
    private String password; // 비밀번호

    @NotNull
    @Length(min = 2)
    private String name; // 회원 이름

    @NotNull
    @Email
    private String email; // 이메일

    @NotNull
    @Length(min = 11)
    private String phone; // 휴대폰번호

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private String birth; // yyyy-MM-dd

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String memberId, String password, String name, String email, String phone, Gender gender, String birth, Authority authority) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.authority = authority;
    }
}
