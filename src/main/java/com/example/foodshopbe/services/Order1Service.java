package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.Order1DTO;
import com.example.foodshopbe.dtos.OrderDetail1DTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.models.*;
import com.example.foodshopbe.repositories.Order1Repository;
import com.example.foodshopbe.repositories.OrderDetail1Repository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.services.Imp.IOrder1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Order1Service implements IOrder1Service {
    private final UserRepository userRepository;
    private final Order1Repository order1Repository;
    private final OrderDetail1Repository orderDetail1Repository;

    @Override
    public Order1 createOrder(Order1DTO order1DTO) throws Exception {
        Optional<User> userOptional = userRepository.findById(order1DTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy user với id " + order1DTO.getUserId());
        }
        User user = userOptional.get();
        Order1 order = Order1.builder()
                .user(user)
                .note(order1DTO.getNote())
                .orderDate(order1DTO.getOrderDate())
                .isActive(true)
                .totalMoney(order1DTO.getTotalMoney())
                .build();
        order1Repository.save(order);

        List<OrderDetail1> orderDetail1s = new ArrayList<>();
        for (OrderDetail1DTO orderDetail1DTO : order1DTO.getOrderDetail1DTOList()) {

            OrderDetail1 orderDetail1 = new OrderDetail1();
            orderDetail1.setOrder1(order);
            orderDetail1.setQuantity(orderDetail1DTO.getQuantity());
            orderDetail1.setPrice(orderDetail1DTO.getPrice());
            orderDetail1.setNote(orderDetail1DTO.getNote());
            orderDetail1.setName(orderDetail1DTO.getName());
            orderDetail1s.add(orderDetail1);
        }

        orderDetail1Repository.saveAll(orderDetail1s);
        order.setOrderDetails1(orderDetail1s);
        return order;
    }

    @Override
    public Order1 getOrderById(int id) throws Exception {
        return order1Repository.findById(id)
                .orElseThrow(()-> new DataNotFoundException(
                        "Khong tim thay product voi id: " + id));
    }


    @Override
    public List<Order1> getFullOrders() {
        return order1Repository.findAll();
    }
}
