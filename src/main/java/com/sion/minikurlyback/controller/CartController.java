package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.CartItemDetailDto;
import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/new")
    public ResponseEntity addCart(@Valid @RequestBody CartItemDto cartItemDto, @AuthenticationPrincipal String memberId) {
        Long savedItemId = cartService.addCart(cartItemDto, memberId);
        return ResponseEntity.ok().body(savedItemId);
    }

    @PostMapping("/cart/update")
    public ResponseEntity updateCart(@RequestBody CartItemDto cartItemDto) {
        cartService.updateItemCount(cartItemDto.getCartItemId(), cartItemDto.getCount());
        return ResponseEntity.ok().body("count changed successfully : " + cartItemDto.getCartItemId());
    }

    @PostMapping("/cart/delete/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

    @GetMapping("/my-cart")
    public ResponseEntity getMyCart(@AuthenticationPrincipal String memberId) {
        List<CartItemDetailDto> cartList = cartService.getMyCartList(memberId);
        return ResponseEntity.ok().body(cartList);
    }

}
