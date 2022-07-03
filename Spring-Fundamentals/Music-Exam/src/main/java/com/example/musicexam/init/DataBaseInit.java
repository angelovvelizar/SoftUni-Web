package com.example.musicexam.init;

import com.example.musicexam.service.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit  implements CommandLineRunner {
    private final ArtistService artistService;

    public DataBaseInit(ArtistService artistService) {
        this.artistService = artistService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.artistService.seedArtists();
    }
}
