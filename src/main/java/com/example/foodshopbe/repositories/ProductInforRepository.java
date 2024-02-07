package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.ProductInfor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInforRepository extends JpaRepository<ProductInfor, Integer> {
    ProductInfor findByName(String name);
}
