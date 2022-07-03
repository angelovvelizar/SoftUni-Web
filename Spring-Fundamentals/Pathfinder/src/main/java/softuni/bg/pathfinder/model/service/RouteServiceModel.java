package softuni.bg.pathfinder.model.service;

import softuni.bg.pathfinder.model.entity.CategoryEntity;
import softuni.bg.pathfinder.model.entity.PictureEntity;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.enums.CategoryNameEnum;
import softuni.bg.pathfinder.model.enums.LevelEnum;

import javax.persistence.*;
import java.util.Set;

public class RouteServiceModel {
    private Long id;
    private String description;
    private String gpxCoordinates;
    private LevelEnum level;
    private String name;
    private UserEntity author;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;
    private Set<PictureEntity> pictures;

    public RouteServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }
}
