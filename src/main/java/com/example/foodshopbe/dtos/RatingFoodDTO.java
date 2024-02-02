package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingFoodDTO {

    private String content;

    @NotNull(message = "Rate Point is required")
    @Min(value = 1, message = "Rate point must be greater than or equal to 1")
    @Max(value = 5, message = "Rate point must max 5")
    @JsonProperty("rate_point")
    private Byte ratePoint;

    @NotNull(message = "usersId is required")
    @Min(value = 1, message = "usersId must be number and greater than or equal to 0")
    @JsonProperty("user_id")
    private Integer usersId;

    @NotNull(message = "productId is required")
    @JsonProperty("product_id")
    @Min(value = 1, message = "productId must be number and greater than or equal to 0")
    private Integer productId;
}
