package com.sion.minikurlyback.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadUtil {
    @Value("${item.image.path}")
    private String imageUploadPath;

    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 서버에 첨부 파일을 생성 후 업로드 성공 시 이미지가 저장된 경로를 반환한다.
     * @param file - 파일
     * @return 이미지 저장 경로 / 실패 시 failed
     */
    public String uploadImageByFile(MultipartFile file) {

        /* 파일이 없으면 null을 반환 */
        if (Objects.isNull(file) || file.isEmpty()) {
            return null;
        }

        /* 저장하려는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(imageUploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        try {
            /* 파일 확장자 */
            String originalFileName = file.getOriginalFilename();
            final String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
            final String fileName = getRandomString() + "." + extension;

            File target = new File(imageUploadPath, fileName);
            file.transferTo(target);

            return imageUploadPath + fileName;
        } catch (Exception e) {
            throw new FileUploadException(e.getMessage(), e);
        }
    }

    public String uploadImageByUrl(String imageUrl) {
        String extension = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
        String fileName = getRandomString();

        String imagePath = imageUploadPath  + fileName + "." + extension;

        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            File file = new File(imagePath);

            ImageIO.write(image, extension, file);
        } catch (Exception e) {
            throw new FileUploadException(e.getMessage(), e);
        }

        return imagePath;
    }

    public void deleteExistingFile(String originalImagePath) {
        if (Objects.isNull(originalImagePath)) {
            return;
        }

        File file = new File(originalImagePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
