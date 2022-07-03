package softuni.bg.pathfinder.service;

import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);

    boolean usernameExists(String username);

    public UserEntity findUserEntityById(Long id);

}
