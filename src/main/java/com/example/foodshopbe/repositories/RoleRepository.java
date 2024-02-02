package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String roleName);
}
