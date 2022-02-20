package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartWrapperDto { // 장바구니에서 주문 선택된 상품 리스트를 받아오는 dto 클래스
    List<CartItemDto> cartItemDtoList;
}
