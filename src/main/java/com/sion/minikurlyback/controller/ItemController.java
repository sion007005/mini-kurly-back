package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.dto.ItemDto;
import com.sion.minikurlyback.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item/register")
    public ResponseEntity<ItemDto> create(@Valid ItemDto itemDto, Long categoryId) {
        return ResponseEntity.ok(itemService.create(itemDto, categoryId));
    }

    @GetMapping("/item")
    public ResponseEntity<ItemDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findOneById(id));
    }

    /**
     * 상품 이미지 파일을 업로드 하는 api
     * 파일을 직접 받거나 이미지를 내려받을 url을 파라미터로 받고,
     * 저장된 파일 경로를 반환한다.
     */
    @PostMapping("/image/save")
    public ResponseEntity<String> saveItemImage(
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam String url) {
        String imagePath = itemService.saveItemImage(imageFile, url);
        return ResponseEntity.ok(imagePath);
    }

    @PostMapping("/image/delete")
    public ResponseEntity<Boolean> deleteItemImage(@RequestParam String url) {
        itemService.deleteItemImage(url);
        return ResponseEntity.ok(true);
    }
}
