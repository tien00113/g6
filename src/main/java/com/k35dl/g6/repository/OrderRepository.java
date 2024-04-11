package com.k35dl.g6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND (o.status = 'PLACED' OR o.status = 'CONFIRMED' OR o.status = 'SHIPPED' OR o.status = 'DELIVERED' OR o.status = 'CANCELLED')")
    public List<Order> findUserOrders(@Param("userId") Long userId);

    public Order findByOrderId(String orderId);
}
