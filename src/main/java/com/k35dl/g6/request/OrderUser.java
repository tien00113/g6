package com.k35dl.g6.request;

import java.util.List;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.OrderItem;

import lombok.Data;

@Data
public class OrderUser {
    private List<OrderItem> orderItems;
    private String note;
    private Address shipAddress;
}
