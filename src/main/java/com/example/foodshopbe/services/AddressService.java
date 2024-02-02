package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.AddressDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Address;
import com.example.foodshopbe.models.User;
import com.example.foodshopbe.repositories.AddressRepository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.services.Imp.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    @Override
    public Address createAddress(AddressDTO addressDTO) throws InvalidParamException {
        User existingUser = userRepository
                .findById(addressDTO.getUserId())
                .orElseThrow(
                        ()-> new InvalidParameterException(
                                "khong tim thay user voi id: " + addressDTO.getUserId())
                );
        Address newAddress = Address
                .builder()
                .address1(addressDTO.getAddress1())
                .address2(addressDTO.getAddress2())
                .prefecture(addressDTO.getPrefecture())
                .postId(addressDTO.getPostId())
                .user(existingUser)
                .build();
        return addressRepository.save(newAddress);
    }

    @Override
    public Address getAddressById(int id) throws Exception {
        return addressRepository.findById(id)
                .orElseThrow(
                        () -> new InvalidParameterException("Khong tim thay address voi id " + id)
                );
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address updateAddress(int addressId, AddressDTO addressDTO) throws Exception {
        User existingUser = userRepository
                .findById(addressDTO.getUserId())
                .orElseThrow(
                        ()-> new InvalidParameterException(
                                "khong tim thay user voi id: " + addressDTO.getUserId())
                );
        Address existingAddress = getAddressById(addressId);
        if(existingAddress != null) {
            existingAddress.setPrefecture(addressDTO.getPrefecture());
            existingAddress.setAddress1(addressDTO.getAddress1());
            existingAddress.setAddress2(addressDTO.getAddress2());
            existingAddress.setPostId(addressDTO.getPostId());
            existingAddress.setUser(existingUser);
        }
        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }
}
