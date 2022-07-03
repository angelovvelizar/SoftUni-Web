package com.example.spotifyplaylistapp.model.view;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;

public class SongViewModel {
    private Long id;
    private String performer;
    private String title;
    private Integer duration;
    private StyleEntity style;

    public StyleEntity getStyle() {
        return style;
    }

    public void setStyle(StyleEntity style) {
        this.style = style;
    }

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String durationToString() {
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;

        return String.format("%d:%02d", minutes, seconds);
    }
}
