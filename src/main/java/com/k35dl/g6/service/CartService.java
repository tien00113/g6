package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.Product.Product;

public interface CartService {
    public Cart addToCart(List<Product> products);

    public Cart updateCart(Cart cart, Long cartId);

    public String deleteCart(Long cartId);

    public Cart findCartById(Long cartId);

    public List<Cart> getAllCarts();
}
