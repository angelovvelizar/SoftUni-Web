package com.example.musicexam.model.entity;

import com.example.musicexam.model.entity.enums.BandNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BandNameEnum name;

    @Column(name = "career_information", columnDefinition = "TEXT")
    private String careerInformation;

    public BandNameEnum getName() {
        return name;
    }

    public void setName(BandNameEnum name) {
        this.name = name;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
