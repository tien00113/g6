package com.k35dl.g6.request;

import java.util.List;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.Product.ReViewProduct;

import lombok.Data;

@Data
public class ReviewProductRequest {
    private Order order;
    private List<ReViewProduct> reviewProducts;
}
