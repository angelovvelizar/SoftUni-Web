package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public void initiliazeStyles() {
        if (this.styleRepository.count() == 0) {
            Arrays.stream(StyleNameEnum.values())
                    .forEach(styleNameEnum -> {
                        StyleEntity style = new StyleEntity();
                        style.setName(styleNameEnum);
                        this.styleRepository.save(style);
                    });
        }
    }

    @Override
    public StyleEntity findStyleByName(StyleNameEnum style) {
        return this.styleRepository.findByName(style);
    }
}
