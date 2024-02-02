package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProduct_Id(int productId);
}
