package com.example.battleships.service;

import com.example.battleships.model.CategoryEntity;
import com.example.battleships.model.enums.CategoryNameEnum;

public interface CategoryService {


    void initiliazeCategories();

    CategoryEntity findCategoryByName(CategoryNameEnum category);
}
