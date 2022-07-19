package softuni.bg.pathfinder.service;

import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);

     UserEntity findUserEntityById(Long id);

}
