package softuni.bg.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.enums.LevelEnum;
import softuni.bg.pathfinder.model.service.UserServiceModel;
import softuni.bg.pathfinder.repository.UserRepository;
import softuni.bg.pathfinder.service.UserService;
import softuni.bg.pathfinder.util.CurrentUser;

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
        user.setLevel(LevelEnum.BEGINNER);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findUserEntityByUsernameAndPassword(username,password)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        this.currentUser.setUsername(username);
        this.currentUser.setId(id);
    }

    @Override
    public void logout() {
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
    }

    @Override
    public UserServiceModel findById(Long id) {

        return this.userRepository.findById(id)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean usernameExists(String username) {
        return this.userRepository.existsByUsername(username);
    }


    public UserEntity findUserEntityById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

}
