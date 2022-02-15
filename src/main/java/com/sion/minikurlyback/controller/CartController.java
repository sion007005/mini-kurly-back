package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/new")
    public ResponseEntity addCart(@Valid CartItemDto cartItemDto, Principal principal) {
        Long savedItemId = cartService.addCart(cartItemDto, principal.getName());
        return ResponseEntity.ok().body(savedItemId);
    }

    @PostMapping("/cart/{cartItemId}")
    public ResponseEntity updateCart(@PathVariable Long cartItemId, int count) {
        cartService.updateItemCount(cartItemId, count);
        return ResponseEntity.ok().body(cartItemId);
    }

    @PostMapping("/cart/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }
}
