package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        this.userRepository.save(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public boolean userExists(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username, password);
    }


    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findUserEntityByUsername(userServiceModel.getUsername()).orElse(null);

        if (user != null) {
            this.currentUser.setId(user.getId());
            this.currentUser.setUsername(user.getUsername());
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<EmployeeViewModel> findAllUsersByCountOfOrders() {

        return this.userRepository.findAllByCountOfOrdersDesc()
                .stream()
                .map(userEntity -> this.modelMapper.map(userEntity,EmployeeViewModel.class))
                .collect(Collectors.toList());
    }
}
