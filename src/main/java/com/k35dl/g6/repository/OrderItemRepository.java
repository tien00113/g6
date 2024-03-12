package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k35dl.g6.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
