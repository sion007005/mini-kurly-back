package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.dto.CartOrderDto;
import com.sion.minikurlyback.dto.OrderDetailDto;
import com.sion.minikurlyback.dto.OrderDto;
import com.sion.minikurlyback.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity getOrderPage(@RequestBody CartOrderDto cartOrderDto, @AuthenticationPrincipal String memberId) {
        List<CartItemDto> cartOrderList = cartOrderDto.getCartItemList();

        if (cartOrderList.size() == 0 || cartOrderList == null) {
            return ResponseEntity.badRequest().body("주문이 선택된 상품이 없습니다.");
        }

        OrderDetailDto orderDetails = orderService.getOrderPage(cartOrderDto, memberId);

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
    @PostMapping("/order/cancel/{orderId}")
    public ResponseEntity cancel(@PathVariable("orderId") Long orderId, @AuthenticationPrincipal String memberId) {
        try {
            orderService.cancelOrder(orderId, memberId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("주문 취소 실패 : " + e.getMessage());
        }
        return ResponseEntity.ok().body(true);
    }

    /**
     * 한 건의 주문내역 확인하기
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity getOrderDetail(@PathVariable("orderId") Long orderId) {
        OrderDetailDto orderDetailDto = orderService.findById(orderId);
        return ResponseEntity.ok().body(orderDetailDto);
    }

    /**
     * 모든 주문내역 확인하기
     */
    @GetMapping("/order/list")
    public ResponseEntity getOrderList(@AuthenticationPrincipal String memberId, Pageable pageable) {
        List<OrderDetailDto> orderList = orderService.findAll(memberId, pageable);
        return ResponseEntity.ok().body(orderList);
    }
}
