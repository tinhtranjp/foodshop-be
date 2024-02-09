package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @NotBlank(message = "phone number is required")
    @Size(min=5, max = 20, message = "phone number must be between 5 and 20 characters")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")

    private Float totalMoney;

    @JsonProperty("shipping_method")
    private  String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("payment_method")
    private String paymentMethod;

    private Float discount;

    @NotNull(message = "userId is required")
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("cart_items")
    private List<CartItemDTO> cartItems;
}
