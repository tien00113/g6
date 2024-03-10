package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
