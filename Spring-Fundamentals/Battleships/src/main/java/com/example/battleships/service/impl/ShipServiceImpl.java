package com.example.battleships.service.impl;

import com.example.battleships.model.ShipEntity;
import com.example.battleships.model.binding.FireBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.view.ShipViewModel;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.service.CategoryService;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, CategoryService categoryService, CurrentUser currentUser, UserService userService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
        this.userService = userService;
    }


    @Override
    public boolean shipExists(String shipName) {
        return this.shipRepository.existsByName(shipName);
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        ShipEntity ship = this.modelMapper.map(shipServiceModel, ShipEntity.class);
        ship.setCategory(this.categoryService.findCategoryByName(shipServiceModel.getCategory()));
        ship.setUser(this.userService.findUserById(currentUser.getId()));
        System.out.println();

        this.shipRepository.save(ship);
    }

    @Override
    public List<ShipViewModel> getAllShipsByStatus() {
        return this.shipRepository.getAllByStatus()
                .stream()
                .map(mapToShipEntity())
                .collect(Collectors.toList());
    }


    @Override
    public List<ShipViewModel> getAllDefenderShips() {
        return this.shipRepository.getAllByStatus()
                .stream()
                .filter(shipEntity -> !shipEntity.getUser().getId().equals(this.currentUser.getId()))
                .map(mapToShipEntity())
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipViewModel> getAllAttackerShips() {
        return this.shipRepository.getAllByStatus()
                .stream()
                .filter(shipEntity -> shipEntity.getUser().getId().equals(this.currentUser.getId()))
                .map(mapToShipEntity())
                .collect(Collectors.toList());
    }

    @Override
    public void fight(FireBindingModel fireBindingModel) {
        Optional<ShipEntity> attacker = this.shipRepository.findById(fireBindingModel.getAttackerId());
        Optional<ShipEntity> defender = this.shipRepository.findById(fireBindingModel.getDefenderId());

        if(attacker.isEmpty() || defender.isEmpty()){
            return;
        }

        ShipEntity _defender = defender.get();
        _defender.setHealth(_defender.getHealth() - attacker.get().getPower());

        if(_defender.getHealth() <= 0 ){
            this.shipRepository.delete(_defender);
            return;
        }

        this.shipRepository.save(_defender);
    }

    private Function<ShipEntity, ShipViewModel> mapToShipEntity() {
        return shipEntity -> this.modelMapper.map(shipEntity, ShipViewModel.class);
    }


}
