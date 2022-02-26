package com.sion.minikurlyback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberIdx; // 회원테이블 pk

    @Column(nullable = false)
    private String addressBasic;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String zipCode;

    @Column(name="is_main_address", columnDefinition = "boolean default true", nullable = false)
    private Boolean mainAddress; // 기본배송지 여부
}
