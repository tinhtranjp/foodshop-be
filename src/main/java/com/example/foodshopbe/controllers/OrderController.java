package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.CategoryDTO;
import com.example.foodshopbe.dtos.OrderDTO;
import com.example.foodshopbe.models.Category;
import com.example.foodshopbe.models.Order;
import com.example.foodshopbe.services.Imp.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService iOrderService;

    @GetMapping( "")
    public ResponseEntity<?> getOrders(
            ) {
        try {
            List<Order> orderList = iOrderService.getFullOrders();
            return  ResponseEntity.ok(orderList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping( "")
    public ResponseEntity<?> insertOrder(
            @Validated
            @RequestBody OrderDTO orderDTO,
            BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages =
                        result.getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Order newOrder = iOrderService.createOrder(orderDTO);
            return  ResponseEntity.ok(newOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
