package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.Order1DTO;
import com.example.foodshopbe.models.Order1;
import com.example.foodshopbe.models.OrderDetail1;
import com.example.foodshopbe.models.Product;

import java.util.List;

public interface IOrder1Service {
    Order1 createOrder (Order1DTO order1DTO) throws  Exception;
    Order1 getOrderById(int id) throws Exception;

    List<Order1> getFullOrders();
}
