package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.dto.UserDto;
import com.sion.minikurlyback.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames={"userId","email", "phone"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // PK
    private String userId; // 회원아이디
    private String password; // 비밀번호
    private String name; // 회원 이름
    private String email; // 이메일
    private String phone; // 휴대폰번호
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별
    private String birth; // yyyy-MM-dd

    @Builder
    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.gender = userDto.getGender();
        this.birth = userDto.getBirth();
    }
}
