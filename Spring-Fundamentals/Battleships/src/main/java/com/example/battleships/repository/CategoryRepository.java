package com.example.battleships.repository;

import com.example.battleships.model.CategoryEntity;
import com.example.battleships.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    Optional<CategoryEntity> findCategoryEntityByName(CategoryNameEnum name);
}
