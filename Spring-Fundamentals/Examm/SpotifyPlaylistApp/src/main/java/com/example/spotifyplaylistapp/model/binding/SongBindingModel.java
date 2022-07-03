package com.example.spotifyplaylistapp.model.binding;

import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongBindingModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String performer;

    @NotBlank
    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull
    @Positive
    private Integer duration;

    @NotNull
    private StyleNameEnum style;

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
