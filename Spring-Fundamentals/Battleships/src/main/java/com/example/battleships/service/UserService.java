package com.example.battleships.service;

import com.example.battleships.model.UserEntity;
import com.example.battleships.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean userExists(String username);

    boolean userExists(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    UserEntity findUserById(Long id);
}
