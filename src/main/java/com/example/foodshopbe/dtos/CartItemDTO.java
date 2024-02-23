package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO {

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("quantity")
    private Integer quantity;
}
