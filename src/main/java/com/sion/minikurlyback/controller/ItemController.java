package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.ItemDto;
import com.sion.minikurlyback.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // TODO 파일업로드용 api를 따로 분리
    @PostMapping("/item/register")
    public ResponseEntity<ItemDto> create(
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            ItemDto itemDto) {
        return ResponseEntity.ok(itemService.create(imageFile, itemDto));
    }

    @GetMapping("/item")
    public ResponseEntity<ItemDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findOneById(id));
    }
}
