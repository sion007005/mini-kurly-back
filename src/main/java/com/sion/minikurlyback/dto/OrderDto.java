package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    List<CartItemDto> cartItemDtoList;
}
