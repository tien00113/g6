package com.k35dl.g6.request;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.OrderItem;

import lombok.Data;

@Data
public class OrderNow {
    private OrderItem orderItem;
    private Address address;
    private String note;
}
