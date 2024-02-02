package com.example.foodshopbe.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Name is required")
    @Size(min=3, max = 350, message = "Name must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Float price;

    private String description;

    private String thumbnail;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @Min(value = 0, message = "Listed price must be greater than or equal to 0")
    @JsonProperty("listed_price")
    private Float listedPrice;

    @JsonProperty("is_freeship")
    private Boolean isFreeShip;

    @JsonProperty("is_promotion")
    private Boolean isPromotion;

    private Float discount;


    @JsonProperty("product_infor_id")
    private int productInforId;

    @JsonProperty("category_id")
    private  int categoryId;
}
