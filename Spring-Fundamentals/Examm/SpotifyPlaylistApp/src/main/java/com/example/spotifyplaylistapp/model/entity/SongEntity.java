package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "songs")
public class SongEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(optional = false)
    private StyleEntity style;

    @ManyToMany(mappedBy = "songs")
    private Set<UserEntity> users;

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public void setStyle(StyleEntity style) {
        this.style = style;
    }
}
