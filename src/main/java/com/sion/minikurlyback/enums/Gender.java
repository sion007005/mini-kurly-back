package com.sion.minikurlyback.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자"),
    NONE("표시안함");

    private final String gender;
}
