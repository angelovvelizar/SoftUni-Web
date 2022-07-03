package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;

public interface StyleService {

    void initiliazeStyles();

    StyleEntity findStyleByName(StyleNameEnum style);
}
