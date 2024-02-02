package com.example.foodshopbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "fullName is required")
    @Size(min=3, max = 50, message = "fullName must be between 3 and 50 characters")
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("furigana_name")
    private String furiganaName;

    @NotBlank(message = "email is required")
    @Size(min=3, max = 100, message = "fullName must be between 3 and 50 characters")
    private String email;

    @NotBlank(message = "phone number is required")
    @Size(min=5, max = 20, message = "phone number must be between 5 and 20 characters")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "fax number is required")
    @Size(min=5, max = 20, message = "fax number must be between 5 and 20 characters")
    @JsonProperty("fax_number")
    private String faxNumber;

    private Boolean gender;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("email_accept")
    private Boolean emailAccept;

    @NotBlank(message = "loginId is required")
    @Size(min=5, max = 50, message = "loginId must be between 5 and 20 characters")
    @JsonProperty("login_id")
    private String loginId;

    @NotBlank(message = "password is required")
    @Size(min=5, max = 250, message = "password must be between 5 and 20 characters")
    @JsonProperty("pass_word")
    private String password;

    @JsonProperty("facebook_account_id")
    private int facebookAccountId;

    @JsonProperty("goole_account_id")
    private int googleAccountId;


    @JsonProperty("is_active")
    private Boolean isActive;
}
