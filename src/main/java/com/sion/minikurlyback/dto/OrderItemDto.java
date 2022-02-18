package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Item item;
    private Integer count;

}
