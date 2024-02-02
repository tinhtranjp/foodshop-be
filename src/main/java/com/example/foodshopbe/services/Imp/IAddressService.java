package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.AddressDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Address;

import java.util.List;

public interface IAddressService {
    Address createAddress(AddressDTO addressDTO) throws InvalidParamException;

    Address getAddressById(int id) throws Exception;

    List<Address> getAllAddress();

    Address updateAddress(int addressId, AddressDTO addressDTO) throws Exception;

    void deleteAddress(int addressId);
}
