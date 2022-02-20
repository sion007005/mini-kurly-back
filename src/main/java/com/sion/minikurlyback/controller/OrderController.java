package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

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
