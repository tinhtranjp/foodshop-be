package com.example.foodshopbe.repositories;

import com.example.foodshopbe.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByUser_Id(int userId);
}
