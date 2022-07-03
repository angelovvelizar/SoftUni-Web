package com.example.coffeeshop.init;

import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public DataBaseInit(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initiliazeCategories();
    }
}
