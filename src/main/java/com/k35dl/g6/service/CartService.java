package com.k35dl.g6.service;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.User;
import com.k35dl.g6.request.AddCartItemRequest;

public interface CartService {
    public Cart ceateCart(User user);

    public String addToCart(Long userId, AddCartItemRequest request) throws ProductException, UserException, Exception;

    public Cart findUserCart(Long userId);

    public Cart findCartById(Long cartId) throws Exception;

    public Cart testCart() throws ProductException;

}
