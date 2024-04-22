package com.k35dl.g6.service.Product;

import java.util.List;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.ReViewProduct;

public interface ReViewProductService {
    public List<ReViewProduct> createReview(User user,Order order, List<ReViewProduct> reViewProducts);
}
