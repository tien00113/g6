package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.exceptions.OrderException;
import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.OrderItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Order.OrderStatus;

public interface OrderService {
    public Order findOrderById(Long orderId) throws OrderException;

    public Order findOrderByOrderId(String orderId) throws OrderException;

    public Order orderNow(User user, Address shipAdress, OrderItem orderItem, String note);

    public Order createOrder(User user, Address shipAdress,List<Long> selectedCartItemIds);

    public Order orderUser(User user, Address shipAddress, List<OrderItem> orderItems, String note);

    public List<Order> userOrderHistory(Long userId);

    public Order updateOrderStatus (String orderId, OrderStatus status);

    public Order placeOrder(Long orderId) throws OrderException;

    public Order confirmOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public void deleteOrder(Long orderId) throws OrderException;

    public List<Order> getALlOrders() throws OrderException;
}
