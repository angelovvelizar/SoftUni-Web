package softuni.bg.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.CategoryEntity;
import softuni.bg.pathfinder.model.enums.CategoryNameEnum;
import softuni.bg.pathfinder.repository.CategoryRepository;
import softuni.bg.pathfinder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum name) {
        return this.categoryRepository.findCategoryEntityByName(name);
    }
}
