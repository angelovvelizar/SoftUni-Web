package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(name = "needed_time", nullable = false)
    private Integer neededTime;

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
