package softuni.bg.pathfinder.model.view;

import softuni.bg.pathfinder.model.entity.PictureEntity;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class RouteViewModel {
    private Long id;
    private String description;
    private String name;
    private String pictureUrl;
    private String videoUrl;
    private UserEntity author;
    private LevelEnum level;
    private Set<PictureEntity> pictures;

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
