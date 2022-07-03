package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;

import java.util.List;

public interface SongService {
    boolean performerExists(String performer);

    void addSong(SongServiceModel songServiceModel);

    List<SongViewModel> getAllSongs();

    SongEntity findById(Long id);
}
