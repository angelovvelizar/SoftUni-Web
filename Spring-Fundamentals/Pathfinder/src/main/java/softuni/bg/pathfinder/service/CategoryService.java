package softuni.bg.pathfinder.service;

import softuni.bg.pathfinder.model.entity.CategoryEntity;
import softuni.bg.pathfinder.model.enums.CategoryNameEnum;

public interface CategoryService {


    CategoryEntity findCategoryByName(CategoryNameEnum name);
}
