package com.example.foodshopbe.respons;

import com.example.foodshopbe.models.Address;
import com.example.foodshopbe.models.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    @JsonProperty("user_id")
    private int userId;

    private String roleName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("furigana_name")
    private String furiganaName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("fax_number")
    private String faxNumber;

    private Boolean gender;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("login_id")
    private String loginId;


    @JsonProperty("address")
    private List<AddressRespon> addressList;

}
