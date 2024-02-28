package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.OrderDTO;
import com.example.foodshopbe.models.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder (OrderDTO orderDTO) throws  Exception;

    List<Order> getFullOrders();

}
