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
public class AddressDTO {

    @NotBlank(message = "postId is required")
    @Size(min = 3, max = 20, message = "postId must be between 3 and 20 characters")
    @JsonProperty("post_id")
    private String postId;

    @NotBlank(message = "prefecture is requied")
    @Size(min = 3, max = 100, message = "prefecture must be between 3 and 100 characters")
    private String prefecture;

    @NotBlank(message = "address1 is requied")
    @Size(min = 3, max = 100, message = "address1 must be between 3 and 100 characters")
    private String address1;

    @NotBlank(message = "address2 is requied")
    @Size(min = 3, max = 100, message = "address2 must be between 3 and 100 characters")
    private String address2;

    @JsonProperty("user_id")
    private int userId;
}
