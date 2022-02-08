package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.ItemDto;
import com.sion.minikurlyback.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item/register")
    public ResponseEntity<ItemDto> create(
            @Valid @RequestBody ItemDto itemDto,
            Long categoryId
            ) {
        return ResponseEntity.ok(itemService.create(itemDto, categoryId));
    }

    @GetMapping("/item")
    public ResponseEntity<ItemDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findOneById(id));
    }
}
