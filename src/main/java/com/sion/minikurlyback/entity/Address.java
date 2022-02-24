package com.sion.minikurlyback.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userIdx; // 회원테이블 pk
    private String addressBasic;
    private String addressDetail;
    private String zipCode;
    private Boolean mainAddress; // 기본배송지 여부
}
