package com.k35dl.g6.request;

import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long productId;
    private int quantity;
    private SizeOption sizeOption;
    private ToppingOption toppingOption;
}
