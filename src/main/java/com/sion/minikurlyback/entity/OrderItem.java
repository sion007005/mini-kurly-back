package com.sion.minikurlyback.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer orderPrice;
    private Integer count;

    public static OrderItem createOrderItem(Order order, Item item, Integer orderPrice, Integer count) {
       OrderItem orderItem = new OrderItem(order, item, orderPrice, count);
       return orderItem;
    }

    private OrderItem(Order order, Item item, Integer orderPrice, Integer count) {
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

}
