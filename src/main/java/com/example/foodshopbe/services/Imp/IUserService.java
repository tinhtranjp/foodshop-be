package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.UserDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.models.Order;
import com.example.foodshopbe.models.User;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;

    String login(String loginId, String password, Integer roleId) throws Exception;
    List<User> getAllUser();


}
