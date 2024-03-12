package com.k35dl.g6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.OrderItem;
import com.k35dl.g6.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplement implements OrderItemService{

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        
        return orderItemRepository.save(orderItem);
    }
    
}
