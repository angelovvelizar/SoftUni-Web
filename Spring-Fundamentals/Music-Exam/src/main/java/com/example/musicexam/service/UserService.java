package com.example.musicexam.service;

import com.example.musicexam.model.service.UserServiceModel;



public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    boolean userExistsByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    boolean userIsLoggedIn();

    void logout();
}
