package com.example.musicexam.service.impl;

import com.example.musicexam.repository.AlbumRepository;
import com.example.musicexam.service.AlbumService;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
