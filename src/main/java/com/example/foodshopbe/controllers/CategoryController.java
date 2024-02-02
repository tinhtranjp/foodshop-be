package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.CategoryDTO;
import com.example.foodshopbe.models.Category;
import com.example.foodshopbe.services.Imp.ICategoryService;
import com.example.foodshopbe.services.Imp.ImageServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @GetMapping("") //http://localhost:8088/api/v1/categories
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categorieList = iCategoryService.getAllCategory();
        return  ResponseEntity.ok(categorieList);
    }

    @PostMapping( "")
    public ResponseEntity<?> insertCategory(
            @Validated
            @RequestBody  CategoryDTO categoriesDTO,
            BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages =
                        result.getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Category newCategory = iCategoryService.createCategory(categoriesDTO);
            return  ResponseEntity.ok(newCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable int id,
            @Validated @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages =
                        result.getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            Category updatedCategory = iCategoryService.updateCategory(id, categoryDTO);

            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        iCategoryService.deleteCategory(id);
        return  ResponseEntity.ok("this is delete Category: " + id);
    }


}
