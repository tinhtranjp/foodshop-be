package com.example.foodshopbe.respons;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRespon {

    @JsonProperty("address_id")
    private int addressId;

    @JsonProperty("post_id")
    private String postId;

    private String prefecture;

    private String address1;

    private String address2;
}
