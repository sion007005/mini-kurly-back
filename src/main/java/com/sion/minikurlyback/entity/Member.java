package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.Authority;
import com.sion.minikurlyback.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // PK

    @Column(unique = true, nullable = false)
    private String memberId; // 회원아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // 회원 이름

    @Column(unique = true, nullable = false)
    private String email; // 이메일

    @Column(unique = true, nullable = false)
    private String phone; // 휴대폰번호

    private String birth; // yyyy-MM-dd

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

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
