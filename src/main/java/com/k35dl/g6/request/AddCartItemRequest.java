package com.k35dl.g6.request;

import java.util.Set;

import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long productId;
    private SizeOption sizeOption;
    private Set<ToppingOption> toppingOption;
    private int quantity;
    // private int price;
}
