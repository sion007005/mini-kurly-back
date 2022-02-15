package com.sion.minikurlyback.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart(member);
        return cart;
    }

    private Cart(Member member) {
        this.member = member;
    }

    protected Cart() {}
//    @Builder
//    public Cart(Member member) {
//        this.member = member;
//    }
}
