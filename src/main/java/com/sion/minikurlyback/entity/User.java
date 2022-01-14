package com.sion.minikurlyback.entity;

import javax.persistence.*;

@Entity
@Table(
        name = "user",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"user_id","email", "phone"}
                )
        }
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // PK
    private String user_id; // 회원아이디
    private String password; // 비밀번호
    private String name; // 회원 이름
    private String email; // 이메일
    private String phone; // 휴대폰번호
    private Gender gender; // 성별
    private String birth; // YY-MM-DD
    private String recommendedBy; // 추천인 아이디
    private Boolean agreedAllTerms; // 약관동의여부

    @Embedded
    private BaseInfo baseInfo;
}
