package com.sion.minikurlyback.service;

import com.sion.minikurlyback.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ItemImageService {
    private final FileUploadUtil fileUploadUtil;

    public String saveItemImage(MultipartFile imageFile, String url) {
        String savedImagePath = "";
        if (imageFile == null || imageFile.isEmpty()) {
            savedImagePath = fileUploadUtil.uploadImageByUrl(url);
        } else {
            savedImagePath = fileUploadUtil.uploadImageByFile(imageFile);
        }

        return savedImagePath;
    }

    public String uploadImageFile(MultipartFile imageFile) {
        String savedImagePath = fileUploadUtil.uploadFileToS3(imageFile);
        return savedImagePath;
    }

    public void deleteItemImage(String path) {
        fileUploadUtil.deleteExistingFile(path);
    }

}
