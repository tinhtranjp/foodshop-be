package com.example.foodshopbe.respons;

import com.example.foodshopbe.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;

    @JsonProperty("user")
    private UserResponse userResponse;
}
