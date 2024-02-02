package com.example.foodshopbe.services.Imp;


import com.example.foodshopbe.dtos.RatingFoodDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.RatingFood;

import java.util.List;

public interface IRatingFoodService {

    RatingFood createRatingFood(RatingFoodDTO ratingFoodDTO) throws InvalidParamException;

    RatingFood getRatingFoodById(int id) throws Exception;

    List<RatingFood> getAllRatingFood();

    RatingFood updateRatingFood(int ratingId, RatingFoodDTO ratingFoodDTO) throws Exception;

    void deleteRatingFood(int ratingId);
}
