package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.CartItemDTO;
import com.example.foodshopbe.dtos.OrderDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.models.*;
import com.example.foodshopbe.repositories.OrderDetailRepository;
import com.example.foodshopbe.repositories.OrderRepository;
import com.example.foodshopbe.repositories.ProductRepository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.services.Imp.IOrderService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public Order createOrder(OrderDTO orderDTO) throws Exception {

        User user = userRepository.findById(orderDTO.getUserId());
        if(user == null) {
            throw new RuntimeException("khong tim thay user voi id " + orderDTO.getUserId());
        }

        Order order = Order.builder()
                .user(user)
                .email(orderDTO.getEmail())
                .discount(orderDTO.getDiscount())
                .fullName(orderDTO.getFullName())
                .note(orderDTO.getNote())
                .shippingAddress(orderDTO.getShippingAddress())
                .shippingMethod(orderDTO.getShippingMethod())
                .totalMoney(orderDTO.getTotalMoney())
                .phoneNumber(orderDTO.getPhoneNumber())
                .paymentMethod(orderDTO.getPaymentMethod())
                .isActive(true)
                .status(OrderStatus.PENDING)
                .trackingNumber("")
                .orderDate(LocalDate.now().atStartOfDay())
                .build();

        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be at least today !");
        }
        order.setShippingDate(shippingDate);
        orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItemDTO cartItemDTO : orderDTO.getCartItems()) {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);


            int productId = cartItemDTO.getProductId();
            int quantity = cartItemDTO.getQuantity();
            Float totalMoney = cartItemDTO.getTotalMoney();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + productId));


            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setTotalMoney(totalMoney);
            orderDetails.add(orderDetail);
        }

        orderDetailRepository.saveAll(orderDetails);

        return order;
    }
}
