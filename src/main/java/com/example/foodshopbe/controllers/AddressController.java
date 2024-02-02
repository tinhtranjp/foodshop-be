package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.AddressDTO;
import com.example.foodshopbe.dtos.ProductsInforDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Address;
import com.example.foodshopbe.repositories.AddressRepository;
import com.example.foodshopbe.services.Imp.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService iAddressService;

    @GetMapping("") //http://localhost:8088/api/v1/address/get-all
    public ResponseEntity<?> getAllAddress() {
        List<Address> addressList = iAddressService.getAllAddress();
        return  ResponseEntity.ok(addressList);
    }

    @PostMapping("")
    public ResponseEntity<?> insertAddress(@Validated @RequestBody AddressDTO addressDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages =
                    result.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try{
            Address newAddress = iAddressService.createAddress(addressDTO);
            return  ResponseEntity.ok(newAddress);
        } catch (InvalidParamException invalidParamException) {
            return ResponseEntity.badRequest().body(invalidParamException.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(

            @PathVariable int id,
            @RequestBody AddressDTO addressDTO) {
        try {
            Address updatedAddress = iAddressService.updateAddress(id, addressDTO);
            return ResponseEntity.ok(updatedAddress);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable int id) {
        iAddressService.deleteAddress(id);
        return  ResponseEntity.ok("Xoa thanh cong address co id:" + id);
    }
}
