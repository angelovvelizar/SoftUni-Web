package com.example.battleships.service;

import com.example.battleships.model.binding.FireBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    boolean shipExists(String shipName);

    void addShip(ShipServiceModel shipServiceModel);

    List<ShipViewModel> getAllShipsByStatus();

    List<ShipViewModel> getAllDefenderShips();

    List<ShipViewModel> getAllAttackerShips();

    void fight(FireBindingModel fireBindingModel);
}
