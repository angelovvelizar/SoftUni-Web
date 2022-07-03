package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class StyleEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public StyleNameEnum getName() {
        return name;
    }

    public void setName(StyleNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
