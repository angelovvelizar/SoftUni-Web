package com.example.battleships.model;

import com.example.battleships.model.enums.CategoryNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
