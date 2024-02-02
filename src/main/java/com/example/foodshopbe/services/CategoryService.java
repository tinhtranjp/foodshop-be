package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.CategoryDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Category;
import com.example.foodshopbe.repositories.CategoryRepository;
import com.example.foodshopbe.services.Imp.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) throws InvalidParamException {
        boolean existingCategory = existsByName(categoryDTO.getName());
            if(existingCategory) {
                throw new InvalidParamException("Ten cua category da ton tai");
            }
            Category newCategory = Category
                    .builder()
                    .name(categoryDTO.getName())
                    .build();
            return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(int id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(
                ()-> new DataNotFoundException("Khong thay category voi id: " + id)
        );
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(int id, CategoryDTO categoryDTO) throws Exception {
        Category existingCategory = getCategoryById(id);
        if(existingCategory != null) {
            existingCategory.setName(categoryDTO.getName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
