package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ItemImageController {
    private final ItemImageService itemImageService;

    /**
     * 상품 이미지 파일을 업로드 하는 api(로컬)
     * 파일을 직접 받거나 이미지를 내려받을 url을 파라미터로 받고,
     * 저장된 파일 경로를 반환한다.
     */
    @PostMapping("/image/save")
    public ResponseEntity<String> saveItemImage(
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam String url) {
        String imagePath = itemImageService.saveItemImage(imageFile, url);
        return ResponseEntity.ok(imagePath);
    }

    /**
     * 이미지파일을 AWS S3에 업로드한다.
     */
    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadImageFile(
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        String imagePath = itemImageService.uploadImageFile(imageFile);
        return ResponseEntity.ok(imagePath);
    }

    @PostMapping("/image/delete")
    public ResponseEntity<Boolean> deleteItemImage(@RequestParam String url) {
        itemImageService.deleteItemImage(url);
        return ResponseEntity.ok(true);
    }

}
