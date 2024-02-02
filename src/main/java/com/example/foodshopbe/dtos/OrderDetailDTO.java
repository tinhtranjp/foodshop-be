package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @Min(value = 0, message = "Price money must be >= 0")
    private Float price;

    @Min(value = 1, message = "Quantity must be >=1")
    private int quantity;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    private Float totalMoney;

    @NotNull(message = "orderId is required")
    @JsonProperty("order_id")
    private Integer orderId;
}
