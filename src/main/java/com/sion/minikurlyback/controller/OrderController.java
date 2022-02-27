package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.*;
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

    /**
     * 주문페이지 보여주기(장바구니에서 주문선택한 상품과 사용자의 기본배송지 정보)
     */
    @GetMapping("/order")
    public ResponseEntity getOrderPage(@RequestBody OrderDto orderDto, Principal principal) {
        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItemList();

        if (orderItemDtoList.size() == 0 || orderItemDtoList == null) {
            return ResponseEntity.badRequest().body("주문이 선택된 상품이 없습니다.");
        }

        OrderDto orderDetails = orderService.getOrderPage(orderDto, principal.getName());

        return ResponseEntity.ok().body(orderDetails);
    }

    /**
     * 주문페이지에서 상품 주문하기
     */
    @PostMapping("/order")
    public ResponseEntity order(@RequestBody @Valid OrderDto orderDto, Principal principal) {
        String memberId = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderDto, memberId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("상품 주문 실패");
        }

        return ResponseEntity.ok().body(orderId);
    }
}
