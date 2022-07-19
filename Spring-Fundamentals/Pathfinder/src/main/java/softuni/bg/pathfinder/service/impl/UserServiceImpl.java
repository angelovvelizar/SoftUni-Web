package softuni.bg.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.enums.LevelEnum;
import softuni.bg.pathfinder.model.service.UserServiceModel;
import softuni.bg.pathfinder.repository.UserRepository;
import softuni.bg.pathfinder.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        user.setLevel(LevelEnum.BEGINNER);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findById(Long id) {

        return this.userRepository.findById(id)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    public UserEntity findUserEntityById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

}
