package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.service.UserServiceModel;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final SongService songService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, SongService songService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.songService = songService;
    }


    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        return this.userRepository.existsByUsernameOrEmail(username,email);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        user.setSongs(new HashSet<>());
        this.userRepository.save(user);
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username,password);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findUserEntityByUsername(userServiceModel.getUsername());
        this.currentUser.setId(user.getId());
        this.currentUser.setUsername(user.getUsername());
    }

    @Override
    public boolean userIsLoggedIn() {
        return this.currentUser.getId() != null;
    }

    @Override
    public void addSong(Long id) {
        UserEntity user = this.userRepository.findById(this.currentUser.getId()).orElse(null);
        SongEntity song = this.songService.findById(id);
        user.getSongs().add(song);
        this.userRepository.save(user);

    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void removeAllSongs() {
        UserEntity user = this.userRepository.findById(this.currentUser.getId()).orElse(null);
        user.getSongs().clear();
        this.userRepository.save(user);
    }

}
