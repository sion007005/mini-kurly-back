package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto { // 장바구니에 담을 상품의 아이디와 수량을 전달하는 클래스
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;
    @Min(value = 1, message = "최소 1개 이상 담아주세요.")
    private int count;
}
