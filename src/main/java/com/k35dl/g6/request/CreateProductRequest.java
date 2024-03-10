package com.k35dl.g6.request;

import java.util.List;

import com.k35dl.g6.models.Product.Product;

import lombok.Data;

@Data
public class CreateProductRequest {
    private Product product;
    private List<Long> sizeOptionIds;
    private List<Long> toppingOptionIds;
}
