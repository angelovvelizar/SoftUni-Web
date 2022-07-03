package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    boolean userExists(String username, String password);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

    Optional<UserEntity> findById(Long id);

    List<EmployeeViewModel> findAllUsersByCountOfOrders();
}
