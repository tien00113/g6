package com.k35dl.g6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.request.CreateOrderRequest;
import com.k35dl.g6.service.OrderService;
import com.k35dl.g6.service.UserSerVice;

@RestController
@RequestMapping("/api/order/")
public class OrderController {
    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request, @RequestHeader("Authorization") String jwt){
        User user = userSerVice.findUserByJwt(jwt);

        Order order = orderService.createOrder(user, request.getShippingAddress(), request.getOrderItemIds());
        
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt){

        User user = userSerVice.findUserByJwt(jwt);

        List<Order> orders = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    
}