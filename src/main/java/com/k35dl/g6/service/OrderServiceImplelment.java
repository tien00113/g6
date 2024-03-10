package com.k35dl.g6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Order.OrderStatus;
import com.k35dl.g6.repository.OrderRepository;
import com.k35dl.g6.request.CreatOrderRequest;

@Service
public class OrderServiceImplelment implements OrderService{

    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(User user) {
        Order newOrder = new Order();

        newOrder.setUser(user);

        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userOrderHistory'");
    }

    @Override
    public Order placeOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeOrder'");
    }

    @Override
    public Order confirmOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmOrder'");
    }

    @Override
    public Order shippedOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shippedOrder'");
    }

    @Override
    public Order deliveredOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
    }

    @Override
    public Order cancledOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancledOrder'");
    }

    @Override
    public Order orderNow(Long userId,CreatOrderRequest request) {

        // User user = userSerVice.findUserById(userId);

        // Order order = new Order();

        // OrderStatus orderStatus = 

        // order.setUser(user);
        // order.setTotalPrice(request.getTotalPrice());
        // order.setShippingAddress(request.getShippingAddress());
        // order.setStatus(OrderStatus.PENDING);
        // order.setOrderItems(request.getOrderItems());
        return null;
    }
    
}
