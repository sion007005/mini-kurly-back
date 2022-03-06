package com.sion.minikurlyback.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sion.minikurlyback.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadUtil {
    @Value("${item.image.path}")
    private String imageUploadPath;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    /**
     * 새로운 파일명을 반환
     */
    private String getNewFileName(String originalFileName) {
        /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
        final String newFileName = getRandomString().concat(originalFileName);

        return newFileName;
    }

    private String getRandomString() {
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
            String newFileName = getNewFileName(file.getOriginalFilename());

            File target = new File(imageUploadPath, newFileName);
            file.transferTo(target);

            return imageUploadPath + newFileName;
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

    public String uploadFileToS3(MultipartFile file) {
        String newFileName = getNewFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, newFileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        return amazonS3.getUrl(bucket, newFileName).toString();
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
