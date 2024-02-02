package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.RatingFoodDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Product;
import com.example.foodshopbe.models.RatingFood;
import com.example.foodshopbe.models.User;
import com.example.foodshopbe.repositories.ProductInforRepository;
import com.example.foodshopbe.repositories.ProductRepository;
import com.example.foodshopbe.repositories.RatingFoodRepository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.services.Imp.IRatingFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingFoodService implements IRatingFoodService {
    private final UserRepository userRepository;
    private final RatingFoodRepository ratingFoodRepository;
    private final ProductRepository productRepository;
    @Override
    public RatingFood createRatingFood(RatingFoodDTO ratingFoodDTO) throws InvalidParamException {
        User existingUser = userRepository
                .findById(ratingFoodDTO
                        .getUsersId())
                        .orElseThrow(
                                () -> new InvalidParameterException(
                                        "Khong tim thay user nao voi id " + ratingFoodDTO.getUsersId())
                        );
        Product existingProduct = productRepository
                .findById(ratingFoodDTO.getProductId())
                .orElseThrow(()-> new InvalidParameterException("Khong tim thay product nao voi id " + ratingFoodDTO.getProductId()));

        RatingFood newRatingFood = RatingFood
                .builder()
                .content(ratingFoodDTO.getContent())
                .ratePoint(ratingFoodDTO.getRatePoint())
                .user(existingUser)
                .product(existingProduct)
                .build();
        return ratingFoodRepository.save(newRatingFood);
    }

    @Override
    public RatingFood getRatingFoodById(int id) throws Exception {
        return ratingFoodRepository
                .findById(id)
                .orElseThrow(
                        () -> new InvalidParameterException(
                                "Khong tim thay rating food voi id " + id));
    }

    @Override
    public List<RatingFood> getAllRatingFood() {
        return ratingFoodRepository.findAll();
    }

    @Override
    public RatingFood updateRatingFood(
            int ratingId,
            RatingFoodDTO ratingFoodDTO
    )  throws Exception {
        RatingFood existingRatingFood = getRatingFoodById(ratingId);
        User existingUser = userRepository
                .findById(ratingFoodDTO
                        .getUsersId())
                .orElseThrow(
                        () -> new InvalidParameterException(
                                "Khong tim thay user nao voi id " + ratingFoodDTO.getUsersId())
                );
        Product existingProduct = productRepository
                .findById(ratingFoodDTO.getProductId())
                .orElseThrow(()-> new InvalidParameterException("Khong tim thay product nao voi id " + ratingFoodDTO.getProductId()));

        if (existingRatingFood != null) {
            existingRatingFood.setRatePoint(ratingFoodDTO.getRatePoint());
            existingRatingFood.setContent(ratingFoodDTO.getContent());
            existingRatingFood.setUser(existingUser);
            existingRatingFood.setProduct(existingProduct);
        }
        return ratingFoodRepository.save(existingRatingFood);
    }

    @Override
    public void deleteRatingFood(int ratingId) {
        ratingFoodRepository.deleteById(ratingId);
    }
}
