package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsInforDTO {

    @NotBlank(message = "Name is required")
    @Size(min=3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Meterial is required")
    @Size(min=3, max = 250, message = "Meterial must be between 3 and 250 characters")
    private String material;

    @NotBlank(message = "Amount is required")
    @Size(min=3, max = 50, message = "Amount must be between 3 and 50 characters")
    private String amount;

    @NotBlank(message = "ExpiratetionDate is required")
    @Size(min=3, max = 50, message = "ExpiratetionDate must be between 3 and 50 characters")
    @JsonProperty("expiratetion_date")
    private String expiratetionDate;

    @JsonProperty("storage_method")
    private String storageMethod;

    @JsonProperty("safery_instruc")
    private String saferyInstruc;
}
