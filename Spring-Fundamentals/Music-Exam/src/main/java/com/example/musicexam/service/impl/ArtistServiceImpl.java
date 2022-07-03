package com.example.musicexam.service.impl;

import com.example.musicexam.model.entity.ArtistEntity;
import com.example.musicexam.model.entity.enums.BandNameEnum;
import com.example.musicexam.repository.ArtistRepository;
import com.example.musicexam.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public void seedArtists() {
        if(this.artistRepository.count() == 0){
            List<ArtistEntity> artist = Arrays.stream(BandNameEnum.values())
                    .map(bandNameEnum -> {
                        ArtistEntity artistEntity = new ArtistEntity();
                        artistEntity.setName(bandNameEnum);
                        return artistEntity;
                    }).toList();

            this.artistRepository.saveAll(artist);
        }
    }
}
