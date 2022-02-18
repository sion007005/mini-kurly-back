package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.CartItemDetailDto;
import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.dto.CartOrderDto;
import com.sion.minikurlyback.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/new")
    public ResponseEntity addCart(@Valid @RequestBody CartItemDto cartItemDto, Principal principal) {
        Long savedItemId = cartService.addCart(cartItemDto, principal.getName());
        return ResponseEntity.ok().body(savedItemId);
    }

    @PostMapping("/cart/update/{cartItemId}")
    public ResponseEntity updateCart(@PathVariable Long cartItemId, @RequestBody CartItemDto cartItemDto) {
        cartService.updateItemCount(cartItemId, cartItemDto.getCount());
        return ResponseEntity.ok().body(cartItemId);
    }

    @PostMapping("/cart/delete/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

    @GetMapping("/my-cart")
    public ResponseEntity getMyCart(Principal principal) {
        List<CartItemDetailDto> cartList = cartService.getMyCartList(principal.getName());
        return ResponseEntity.ok().body(cartList);
    }

    @PostMapping("/cart/order")
    public ResponseEntity order(@RequestBody CartOrderDto cartOrderDto, Principal principal) {
        List<CartItemDto> cartItemDtoList = cartOrderDto.getCartItemDtoList();

        if (cartItemDtoList.size() == 0 || cartItemDtoList == null) {
            return ResponseEntity.badRequest().body("주문이 선택된 상품이 없습니다.");
        }

        // TODO 주문권한 확인

        Long orderId = cartService.orderCartItem(cartItemDtoList, principal.getName());
        return ResponseEntity.ok().body(orderId);
    }
}
