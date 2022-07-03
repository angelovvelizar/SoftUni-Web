package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrdersByPrice();

    void readyOrder(Long id);
}
