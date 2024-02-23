package com.example.foodshopbe.services.Imp;
import com.example.foodshopbe.dtos.OrderDTO;
import com.example.foodshopbe.models.Order;
public interface IOrderService {
    Order createOrder (OrderDTO orderDTO) throws  Exception;
}
