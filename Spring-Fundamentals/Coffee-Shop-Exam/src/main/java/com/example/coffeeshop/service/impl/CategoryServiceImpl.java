package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initiliazeCategories() {
        if(categoryRepository.count() == 0){
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(this::persistCategory);
        }
    }

    @Override
    public Optional<CategoryEntity> findCategoryByName(CategoryNameEnum category) {
        return this.categoryRepository.findCategoryEntityByName(category);
    }

    private void persistCategory(CategoryNameEnum categoryNameEnum) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryNameEnum);
        switch (categoryNameEnum){
            case CAKE -> category.setNeededTime(10);
            case DRINK -> category.setNeededTime(1);
            case COFFEE -> category.setNeededTime(2);
            case OTHER -> category.setNeededTime(5);
        }
        this.categoryRepository.save(category);
    }
}
