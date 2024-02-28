package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.Order1DTO;
import com.example.foodshopbe.dtos.OrderDTO;
import com.example.foodshopbe.models.Order;
import com.example.foodshopbe.models.Order1;
import com.example.foodshopbe.models.OrderDetail1;
import com.example.foodshopbe.models.Product;
import com.example.foodshopbe.services.Imp.IOrder1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order1")
@RequiredArgsConstructor
public class Order1Controller {
    private final IOrder1Service iOrder1Service;
    @GetMapping( "")
    public ResponseEntity<?> getAllOrders(
    ) {
        try {
            List<Order1> orderList = iOrder1Service.getFullOrders();
            return  ResponseEntity.ok(orderList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById (
            @PathVariable("id") int orderDetailId
    ) {
        try {
            Order1 orderDetail = iOrder1Service.getOrderById(orderDetailId);
            return ResponseEntity.ok(orderDetail);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping( "")
    public ResponseEntity<?> insertOrder(
            @Validated
            @RequestBody Order1DTO orderDTO,
            BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages =
                        result.getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Order1 newOrder = iOrder1Service.createOrder(orderDTO);
            return  ResponseEntity.ok(newOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
