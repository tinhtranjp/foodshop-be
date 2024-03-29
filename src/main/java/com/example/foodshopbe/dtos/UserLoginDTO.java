package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @JsonProperty("login_id")
    @NotBlank(message = "LoginId is required")
    private String loginId;

    @JsonProperty("pass_word")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @JsonProperty("role_id")
    private Integer roleId;
}
