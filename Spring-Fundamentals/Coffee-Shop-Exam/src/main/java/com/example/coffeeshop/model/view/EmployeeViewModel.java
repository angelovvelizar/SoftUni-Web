package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entity.OrderEntity;

import java.util.Set;

public class EmployeeViewModel {
    private Long id;
    private String username;
    private Set<OrderEntity> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
