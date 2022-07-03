package com.example.spotifyplaylistapp.model.service;

import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;
import java.time.LocalDate;

public class SongServiceModel {
    private Long id;
    private String performer;
    private String title;
    private LocalDate releaseDate;
    private Integer duration;
    private StyleNameEnum style;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public void setStyle(StyleNameEnum style) {
        this.style = style;
    }
}
