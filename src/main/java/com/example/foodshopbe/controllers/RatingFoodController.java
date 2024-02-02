package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.RatingFoodDTO;
import com.example.foodshopbe.models.RatingFood;
import com.example.foodshopbe.services.Imp.IRatingFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/rating-food")
@RequiredArgsConstructor
public class RatingFoodController {

    private final IRatingFoodService iRatingFoodService;

    @GetMapping("") //http://localhost:8088/api/v1/rating-food
    public ResponseEntity<List<RatingFood>> getAllRatingFood() {

        List<RatingFood> ratingFoodList = iRatingFoodService.getAllRatingFood();

        return  ResponseEntity.ok(ratingFoodList);
    }

    @PostMapping("")
    public ResponseEntity<?> insertRatingFood(@Validated @RequestBody RatingFoodDTO ratingFoodDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages =
                    result.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            RatingFood newRatingFood = iRatingFoodService.createRatingFood(ratingFoodDTO);
            return ResponseEntity.ok(newRatingFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRatingFood( @Validated
            @PathVariable int id,
            @RequestBody RatingFoodDTO ratingFoodDTO,
            BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages =
                    result.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            RatingFood updatedRatingFood = iRatingFoodService.updateRatingFood(id,ratingFoodDTO);
            return ResponseEntity.ok(updatedRatingFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRatingFood(@PathVariable int id) {
        return  ResponseEntity.ok("Chua xu ly dau");
    }
}
