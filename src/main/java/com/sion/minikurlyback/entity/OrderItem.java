package com.sion.minikurlyback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
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
       item.removeStock(count);

       return orderItem;
    }

    private OrderItem(Order order, Item item, Integer orderPrice, Integer count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
        this.order = order;
    }

    /**
     * 주문취소된 상품들의 재고를 다시 더해준다.
     */
    public void cancel() {
        getItem().addStock(count);
    }
}
