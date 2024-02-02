package com.example.foodshopbe.services;

import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.repositories.ProductImageRepository;
import com.example.foodshopbe.respons.ProductImgResponse;
import com.example.foodshopbe.respons.ProductResponse;
import com.example.foodshopbe.services.Imp.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceImp {

    private final ProductImageRepository productImageRepository;
    @Override
    public String createStoreFile(MultipartFile file) throws IOException {
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        java.nio.file.Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    @Override
    public List<ProductImgResponse> getImgByProductId(int productId) {
         List<ProductImage> productImageList =  productImageRepository.findByProduct_Id(productId);
        return productImageList.stream().map(ProductImgResponse::fromProductImage).toList();
    }


}
