package com.k35dl.g6.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.OrderException;
import com.k35dl.g6.models.Notifications;
import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.NotificationsRepository;
import com.k35dl.g6.request.CreateOrderRequest;
import com.k35dl.g6.request.OrderNow;
import com.k35dl.g6.request.OrderUser;
import com.k35dl.g6.service.OrderService;
import com.k35dl.g6.service.UserSerVice;

@RestController
// @RequestMapping("/api/order/")
public class OrderController {
    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @PostMapping("/api/order")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request, @RequestHeader("Authorization") String jwt){
        User user = userSerVice.findUserByJwt(jwt);

        Order order = orderService.createOrder(user, request.getShippingAddress(), request.getOrderItemIds());
        
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @PostMapping("/api/order/now")
    public ResponseEntity<Order> createOrderNow(@RequestHeader("Authorization") String jwt, @RequestBody OrderNow request){
        User user = userSerVice.findUserByJwt(jwt);

        Order order = orderService.orderNow(user, request.getAddress(), request.getOrderItem(), request.getNote());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/api/order/create")
    public ResponseEntity<Order> orderUser(@RequestHeader("Authorization") String jwt, @RequestBody OrderUser request){
        User user = userSerVice.findUserByJwt(jwt);

        Order order = orderService.orderUser(user, request.getShipAddress(), request.getOrderItems(), request.getNote());

        Notifications notifications = new Notifications();

        notifications.setMessage("Bạn có đơn hàng mới");
        notifications.setRead(false);
        notifications.setTimestamp(LocalDateTime.now());

        notificationsRepository.save(notifications);

        //gửi thông tin đơn hàng qua websocket
        simpMessagingTemplate.convertAndSend("/topic/orders", order);
        simpMessagingTemplate.convertAndSend("/topic/notifications", notifications);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/api/order/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt){

        User user = userSerVice.findUserByJwt(jwt);

        List<Order> orders = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @PutMapping("/api/order/delivered")
    public ResponseEntity<Order> completedOrder(@RequestBody Long orderId) throws OrderException{
        Order order = orderService.deliveredOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/api/order/cancelled")
    public ResponseEntity<Order> cancelledOrder(@RequestBody Long orderId) throws OrderException{
        Order order = orderService.cancledOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    // @MessageMapping("/newOrder")
    // @SendTo("/topic/orders")
    // public Order newOrder(Order order) {
    //     return order;
    // }

    // @MessageMapping("/notifications")
    // @SendTo("/topic/notifications")
    // public String newNotifications(String msg){
    //     return msg;
    // }
    
}
