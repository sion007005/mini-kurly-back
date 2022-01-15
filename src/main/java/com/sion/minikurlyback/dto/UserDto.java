package com.sion.minikurlyback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sion.minikurlyback.entity.User;
import com.sion.minikurlyback.enums.Gender;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private String birth;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.birth = user.getBirth();
    }
}
