package com.k35dl.g6.request;

import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

import lombok.Data;

@Data
public class OrderNow {
    private Product product;
    private SizeOption sizeOption;
    private int totalPrice;
    private ToppingOption toppingOption;
    private int quantity;
}
