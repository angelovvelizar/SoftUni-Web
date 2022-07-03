package com.example.battleships.service.impl;

import com.example.battleships.model.UserEntity;
import com.example.battleships.model.service.UserServiceModel;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.service.UserService;
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
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean userExists(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findUserEntityByUsernameAndPassword(userServiceModel.getUsername(), userServiceModel.getPassword());
        this.currentUser.setId(user.getId());
        this.currentUser.setUsername(user.getUsername());
    }

    @Override
    public UserEntity findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }


}
