package com.k35dl.g6.request;

import java.util.List;

import com.k35dl.g6.models.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Address shippingAddress;
    private List<Long> orderItemIds;
}
