package com.sion.minikurlyback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sion.minikurlyback.enums.Authority;
import com.sion.minikurlyback.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {
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

    public void updatePassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
