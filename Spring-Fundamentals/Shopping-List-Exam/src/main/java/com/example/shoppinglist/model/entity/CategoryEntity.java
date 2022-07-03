package com.example.shoppinglist.model.entity;

import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Column
    @Enumerated(EnumType.STRING)
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
