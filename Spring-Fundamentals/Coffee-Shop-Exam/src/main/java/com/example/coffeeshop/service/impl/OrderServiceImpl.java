package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CategoryService categoryService, CurrentUser currentUser, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity order = this.modelMapper.map(orderServiceModel, OrderEntity.class);
        order.setCategory(this.categoryService.findCategoryByName(orderServiceModel.getCategory()).orElse(null));
        order.setEmployee(this.userService.findById(currentUser.getId()).orElse(null));

        this.orderRepository.save(order);

        return this.modelMapper.map(order, OrderServiceModel.class);
    }

    @Override
    public List<OrderViewModel> findAllOrdersByPrice() {
        return this.orderRepository.getAllByPriceOrderedDesc()
                .stream()
                .map(orderEntity -> this.modelMapper.map(orderEntity, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}
