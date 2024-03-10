package com.k35dl.g6.request;

import java.time.LocalDateTime;
import java.util.List;

import com.k35dl.g6.models.OrderItem;

import lombok.Data;

@Data
public class CreatOrderRequest {
    private String shippingAddress;
    // private LocalDateTime orderDateTimes;
    private Long userId;
    private List<OrderItem> orderItems;
    private int totalPrice;

}
