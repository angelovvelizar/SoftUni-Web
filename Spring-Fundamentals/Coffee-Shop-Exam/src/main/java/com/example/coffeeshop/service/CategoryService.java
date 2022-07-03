package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {

    void initiliazeCategories();

    Optional<CategoryEntity> findCategoryByName(CategoryNameEnum category);
}
