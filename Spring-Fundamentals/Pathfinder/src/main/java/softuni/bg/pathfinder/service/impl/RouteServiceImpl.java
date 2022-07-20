package softuni.bg.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.CategoryEntity;
import softuni.bg.pathfinder.model.entity.RouteEntity;
import softuni.bg.pathfinder.model.service.RouteServiceModel;
import softuni.bg.pathfinder.model.view.RouteViewModel;
import softuni.bg.pathfinder.repository.RouteRepository;
import softuni.bg.pathfinder.service.CategoryService;
import softuni.bg.pathfinder.service.RouteService;
import softuni.bg.pathfinder.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Transactional
    @Override
    public List<RouteViewModel> findAllRoutes() {
        return this.routeRepository.findAll()
                .stream()
                .map(routeEntity -> {
                    RouteViewModel routeViewModel = this.modelMapper.map(routeEntity, RouteViewModel.class);
                    routeViewModel.setPictureUrl(routeEntity.getPictures().isEmpty()
                            ? "/images/pic4.jpg" : routeEntity.getPictures().stream().findAny().get().getUrl());
                    return routeViewModel;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RouteViewModel findRouteById(Long id) {
        return this.routeRepository
                .findById(id)
                .map(routeEntity -> this.modelMapper.map(routeEntity, RouteViewModel.class)).orElse(null);
    }

    @Override
    public boolean existsByName(String name) {
        return this.routeRepository.existsByName(name);
    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        RouteEntity route = this.modelMapper.map(routeServiceModel, RouteEntity.class);

        //TODO: set current user to routeAuthor

        List<CategoryEntity> categories = routeServiceModel.getCategories()
                .stream()
                .map(this.categoryService::findCategoryByName)
                .collect(Collectors.toList());

        route.setCategories(categories);
        this.routeRepository.save(route);
    }
}
