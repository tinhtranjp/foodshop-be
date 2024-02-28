package com.example.foodshopbe.dtos;

import com.example.foodshopbe.models.OrderDetail1;
import com.example.foodshopbe.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order1DTO {
    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    private Float totalMoney;

    private String note;

    @JsonProperty("order_date")
    private LocalDate orderDate;

    @JsonProperty("is_active")
    private Boolean isActive;

    @NotNull(message = "userId is required")
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("order_list")
    private List<OrderDetail1DTO> orderDetail1DTOList;
}
