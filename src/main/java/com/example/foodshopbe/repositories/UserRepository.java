package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByLoginId(String loginId);
    Boolean existsByEmail(String email);
    Optional<User> findByLoginId(String loginId);

    Optional<User> findById(Integer userId);
}
