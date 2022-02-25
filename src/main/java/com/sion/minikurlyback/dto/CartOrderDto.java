package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderDto {
    private List<CartItemDto> cartItemDtoList; // TODO 장바구니에서 직접 주문까지 처리하는 로직은 없앨 거니까 삭제하기
    private List<CartItemDetailDto> cartItemDetailDtoList;
}
