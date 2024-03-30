package com.k35dl.g6.request;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long productId;
    private int quantity;
    // private int price;
}
