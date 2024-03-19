package com.k35dl.g6.request;

import java.util.List;

import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ProductImage;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

import lombok.Data;

@Data
public class CreateProductRequest {
    private Product product;
    private List<SizeOption> sizeOptions;
    private List<ToppingOption> toppingOptions;
    private List<ProductImage> productImages;
}
