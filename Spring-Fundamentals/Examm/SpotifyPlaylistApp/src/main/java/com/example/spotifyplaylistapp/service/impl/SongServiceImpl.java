package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
    }


    @Override
    public boolean performerExists(String performer) {
        return this.songRepository.existsByPerformer(performer);
    }

    @Override
    public void addSong(SongServiceModel songServiceModel) {
        SongEntity song = this.modelMapper.map(songServiceModel, SongEntity.class);
        song.setStyle(this.styleService.findStyleByName(songServiceModel.getStyle()));
        this.songRepository.save(song);
    }

    @Override
    public List<SongViewModel> getAllSongs() {
        return this.songRepository.findAll()
                .stream()
                .map(songEntity -> this.modelMapper.map(songEntity, SongViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SongEntity findById(Long id) {
        return this.songRepository.findById(id).orElse(null);
    }
}
