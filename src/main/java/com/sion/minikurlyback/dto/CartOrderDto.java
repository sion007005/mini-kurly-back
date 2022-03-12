package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
/**
 * 장바구니에서 주문 선택된 상품을 전달하는 dto
 */
public class CartOrderDto {
    private List<CartItemDto> cartItemList;
}
