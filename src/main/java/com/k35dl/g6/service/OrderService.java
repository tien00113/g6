package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.request.CreatOrderRequest;

public interface OrderService {
    public Order orderNow(Long userId, CreatOrderRequest request);
    public Order createOrder(User user);

    public List<Order> userOrderHistory(Long userId);

    public Order placeOrder(Long orderId);

    public Order confirmOrder(Long orderId);

    public Order shippedOrder(Long orderId);

    public Order deliveredOrder(Long orderId);

    public Order cancledOrder(Long orderId);
}
