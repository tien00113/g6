package com.k35dl.g6.controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.OrderException;
import com.k35dl.g6.models.Order;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.OrderService;
import com.k35dl.g6.service.UserSerVice;

@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserSerVice userSerVice;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getALlOrdersHandle() throws OrderException {
        List<Order> orders = orderService.getALlOrders();

        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<Order> confirmOrderHandle(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.confirmOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<Order> shippedOrderHandle(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.shippedOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<Order> deliveredOrderHandle(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.deliveredOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancled")
    public ResponseEntity<Order> cancledOrderHandle(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.cancledOrder(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandle(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder(orderId);

        ApiResponse response = new ApiResponse();

        response.setMessage("Đã xóa đơn hàng có id" + orderId);
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
