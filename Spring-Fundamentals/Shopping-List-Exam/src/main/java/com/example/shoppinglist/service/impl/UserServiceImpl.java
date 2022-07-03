package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.UserEntity;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.security.CurrentUser;
import com.example.shoppinglist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean userExists(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username,password);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findUserEntityByUsernameAndPassword(userServiceModel.getUsername(), userServiceModel.getPassword());
        this.currentUser.setId(user.getId());
        this.currentUser.setUsername(user.getUsername());
    }
}
