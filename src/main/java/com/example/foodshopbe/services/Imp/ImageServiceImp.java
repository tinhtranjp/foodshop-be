package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.respons.ProductImgResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageServiceImp {
    String createStoreFile(MultipartFile file) throws IOException;
    boolean isImageFile(MultipartFile file);

    List<ProductImgResponse> getImgByProductId(int productId);
}
