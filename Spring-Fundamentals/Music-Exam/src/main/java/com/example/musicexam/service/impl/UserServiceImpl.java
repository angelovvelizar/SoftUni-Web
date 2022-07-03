package com.example.musicexam.service.impl;

import com.example.musicexam.model.entity.UserEntity;
import com.example.musicexam.model.service.UserServiceModel;
import com.example.musicexam.repository.UserRepository;
import com.example.musicexam.security.CurrentUser;
import com.example.musicexam.service.UserService;
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
    public boolean userExistsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistsByUsernameAndPassword(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username,password);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findUserEntityByUsername(userServiceModel.getUsername());

        if(user != null){
            this.currentUser.setId(user.getId());
            this.currentUser.setUsername(user.getUsername());
        }
    }

    @Override
    public boolean userIsLoggedIn() {
        return this.currentUser.getId() != null;
    }

    @Override
    public void logout() {
        if(userIsLoggedIn()){
            this.currentUser.setId(null);
            this.currentUser.setUsername(null);
        }
    }


}
