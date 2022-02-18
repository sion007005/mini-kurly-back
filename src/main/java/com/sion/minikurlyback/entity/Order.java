package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order createOrder(Member member) {
        Order order = new Order(member);
        return order;
    }

    private Order(Member member) {
        this.member = member;
        this.orderStatus = OrderStatus.ORDERED;
    }

}
