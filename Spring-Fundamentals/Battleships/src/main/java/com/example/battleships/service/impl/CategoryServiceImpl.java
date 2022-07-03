package com.example.battleships.service.impl;

import com.example.battleships.model.CategoryEntity;
import com.example.battleships.model.enums.CategoryNameEnum;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initiliazeCategories() {
        if(this.categoryRepository.count() == 0){
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(this::persistCategory);
        }

    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum category) {
        return this.categoryRepository.findCategoryEntityByName(category).orElse(null);
    }

    private void persistCategory(CategoryNameEnum categoryNameEnum) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryNameEnum);
        this.categoryRepository.save(categoryEntity);
    }
}
