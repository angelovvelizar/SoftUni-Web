package softuni.bg.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.bg.pathfinder.model.entity.CategoryEntity;
import softuni.bg.pathfinder.model.enums.CategoryNameEnum;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findCategoryEntityByName(CategoryNameEnum name);
}
