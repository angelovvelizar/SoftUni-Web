package com.example.eventdemo.service;

import com.example.eventdemo.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent evt){
        LOGGER.info("Decreasing inventory for {}", evt.getOrderId());
    }
}
