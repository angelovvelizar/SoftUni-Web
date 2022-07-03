package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.service.UserServiceModel;

public interface UserService {

    boolean existsByUsernameOrEmail(String username, String email);

    void registerUser(UserServiceModel userServiceModel);

    boolean existsByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    boolean userIsLoggedIn();

    void addSong(Long id);

    UserEntity findById(Long id);

    void removeAllSongs();
}
