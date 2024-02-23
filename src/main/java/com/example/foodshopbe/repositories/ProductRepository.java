package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findTop20ByOrderByIdDesc();

    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId IS NULL OR :categoryId = 0 OR p.category.id = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR (:maxPrice = 0 AND p.price > 0) OR p.price <= :maxPrice) " +
            "AND (:isPromotion IS NULL OR COALESCE(p.isPromotion, FALSE) = :isPromotion) " +
            "AND (:isFreeShip IS NULL OR COALESCE(p.isFreeShip, FALSE) = :isFreeShip)")
    Page<Product> searchProducts(
            @Param("categoryId") Long categoryId,
            @Param("keyword") String keyword,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("isPromotion") Boolean isPromotion,
            @Param("isFreeShip") Boolean isFreeShip,
            Pageable pageable);
}