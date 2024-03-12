package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.exceptions.OrderException;
import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;

public interface OrderService {
    public Order findOrderById(Long orderId) throws OrderException;

    public Order orderNow(User user, Address shipAdress, Product product, int quantity);

    public Order createOrder(User user, Address shipAdress,List<Long> selectedCartItemIds);

    public List<Order> userOrderHistory(Long userId);

    public Order placeOrder(Long orderId) throws OrderException;

    public Order confirmOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public void deleteOrder(Long orderId) throws OrderException;

    public List<Order> getALlOrders() throws OrderException;
}
