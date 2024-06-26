package com.k35dl.g6.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.Order.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND (o.status = 'PLACED' OR o.status = 'CONFIRMED' OR o.status = 'SHIPPED' OR o.status = 'DELIVERED' OR o.status = 'CANCELLED')")
    public List<Order> findUserOrders(@Param("userId") Long userId);

    public Order findByOrderId(String orderId);

    @Query("SELECT o FROM Order o WHERE o.status = 'DELIVERED' AND o.updateStatusAt BETWEEN :startDate AND :endDate")
    public List<Order> findAllDeliveredOrdersByCompletionTimeBetween(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    public List<Order> findAllByStatus(OrderStatus status);
}
