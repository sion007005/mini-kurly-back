package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.OrderDto;
import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 주문페이지 보여주기(장바구니에서 주문선택한 상품과 사용자의 기본배송지 정보)
     */
    @GetMapping("/order")
    public ResponseEntity getOrderPage(@RequestBody OrderDto orderDto, @AuthenticationPrincipal String memberId) {
        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItemList();

        if (orderItemDtoList.size() == 0 || orderItemDtoList == null) {
            return ResponseEntity.badRequest().body("주문이 선택된 상품이 없습니다.");
        }

        OrderDto orderDetails = orderService.getOrderPage(orderDto, memberId);

        return ResponseEntity.ok().body(orderDetails);
    }

    /**
     * 주문페이지에서 상품 주문하기
     */
    @PostMapping("/order")
    public ResponseEntity order(@RequestBody @Valid OrderDto orderDto, @AuthenticationPrincipal String memberId) {
        Long orderId;

        try {
            orderId = orderService.order(orderDto, memberId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("상품 주문 실패 : " + e.getMessage());
        }

        return ResponseEntity.ok().body(orderId);
    }

    /**
     * 주문취소
     */
    @PostMapping("/orders/{orderId}/cancel")
    public ResponseEntity cancel(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(true);
    }
}
