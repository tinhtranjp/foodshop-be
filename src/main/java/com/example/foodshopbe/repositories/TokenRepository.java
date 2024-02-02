package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
