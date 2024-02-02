package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.UserDTO;
import com.example.foodshopbe.dtos.UserLoginDTO;
import com.example.foodshopbe.models.Address;
import com.example.foodshopbe.models.User;
import com.example.foodshopbe.repositories.AddressRepository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.respons.AddressRespon;
import com.example.foodshopbe.respons.LoginResponse;
import com.example.foodshopbe.respons.UserResponse;
import com.example.foodshopbe.services.AddressService;
import com.example.foodshopbe.services.Imp.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;
    private final UserRepository userRepository;
    private  final AddressRepository addressRepository;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        try{
            if(result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }

            User user = iUserService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Loi register, Err: "+ e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = iUserService.login(
                    userLoginDTO.getLoginId(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getRoleId() == null ? 2 : userLoginDTO.getRoleId()
            );

            Optional<User> optionalUser = userRepository.findByLoginId(userLoginDTO.getLoginId());
            List<Address> addressList = addressRepository.findByUser_Id(optionalUser.get().getId());

            List<AddressRespon> addressResponList = addressList.stream()
                    .map(address -> AddressRespon.builder()
                            .addressId(address.getId())
                            .postId(address.getPostId())
                            .prefecture(address.getPrefecture())
                            .address1(address.getAddress1())
                            .address2(address.getAddress2())
                            .build())
                    .toList();
            UserResponse userResponse = getUserResponse(optionalUser, addressResponList);

            return ResponseEntity.ok(
                    LoginResponse.builder()
                    .message("Dang nhap thanh cong")
                    .token(token)
                    .userResponse(userResponse)
                    .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                    .message(e.getMessage())
                    .build());
        }

    }

    private static UserResponse getUserResponse(Optional<User> optionalUser, List<AddressRespon> addressResponList) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFullName(optionalUser.get().getFullName());
        userResponse.setLoginId(optionalUser.get().getLoginId());
        userResponse.setEmail(optionalUser.get().getEmail());
        userResponse.setGender(optionalUser.get().getGender());
        userResponse.setAddressList(addressResponList);
        userResponse.setFaxNumber(optionalUser.get().getFaxNumber());
        userResponse.setFuriganaName(optionalUser.get().getFuriganaName());
        userResponse.setPhoneNumber(optionalUser.get().getPhoneNumber());
        userResponse.setDateOfBirth(optionalUser.get().getDateOfBirth());
        userResponse.setUserId(optionalUser.get().getId());
        userResponse.setRoleName(optionalUser.get().getRole().getName());
        return userResponse;
    }

}
