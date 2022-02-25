package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.CartItemDetailDto;
import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.dto.CartOrderDto;
import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 장바구니에서 상품을 선택한 뒤 주문하기를 클릭하면, 주문 페이지를 보여준다.
    // 주문페이지에서는 주문 상품 목록을 보여주고, 결제 수단과 배송지를 입력받는다.
    @GetMapping("/order")
    public ResponseEntity getOrderPage(@RequestBody CartOrderDto cartOrderDto, Principal principal) {
        List<CartItemDto> cartItemDtoList = cartOrderDto.getCartItemDtoList();

        if (cartItemDtoList.size() == 0 || cartItemDtoList == null) {
            return ResponseEntity.badRequest().body("주문이 선택된 상품이 없습니다.");
        }

        List<CartItemDetailDto> itemDetailList = cartOrderDto.getCartItemDetailDtoList();
        return ResponseEntity.ok().body(itemDetailList);
    }
    
    // TODO 주문 페이지에서 배송정보도 받아오도록 개선
    @PostMapping("/item/order")
    public ResponseEntity order(@RequestBody @Valid OrderItemDto orderItemDto, Principal principal) {
        String memberId = principal.getName();
        Long orderId;

        try {
            orderId = orderService.orderOneItem(orderItemDto, memberId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("상품 주문 실패");
        }

        return ResponseEntity.ok().body(orderId);
    }
}
