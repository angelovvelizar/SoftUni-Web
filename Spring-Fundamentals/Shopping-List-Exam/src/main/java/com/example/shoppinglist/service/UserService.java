package com.example.shoppinglist.service;

import com.example.shoppinglist.model.service.UserServiceModel;

public interface UserService {
    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    boolean userExists(String username, String password);

    void loginUser(UserServiceModel userServiceModel);
}
