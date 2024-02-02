package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("is_thumbnail")
    private Boolean isThumbnail = false;

    @JsonProperty("image_url")
    private String imageURL;
}
