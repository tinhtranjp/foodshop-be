package com.example.foodshopbe.controllers;
import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.repositories.ProductImageRepository;
import com.example.foodshopbe.respons.ProductImgResponse;
import com.example.foodshopbe.services.Imp.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceImp imageServiceImp;

    @GetMapping("/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable String imageName) {
        try {
            java.nio.file.Path imagePath = Paths.get("uploads/"+imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new UrlResource(Paths.get("uploads/notfound.jpg").toUri()));
                //return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getImgByProductId(@PathVariable String productId) {
        List<ProductImgResponse> productImageList =  imageServiceImp.getImgByProductId(Integer.parseInt(productId));
        return ResponseEntity.ok(productImageList);
    }
}
