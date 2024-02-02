package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.CategoryDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO) throws InvalidParamException;

    Category getCategoryById(int id) throws Exception;

    List<Category> getAllCategory();
    Category updateCategory(int id, CategoryDTO categoryDTO) throws Exception;

    void deleteCategory(int id);

    boolean existsByName(String name);
}
