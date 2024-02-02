package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
