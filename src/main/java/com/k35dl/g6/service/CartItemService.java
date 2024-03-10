package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.CartItem;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartitem(Long userId, CartItem cartItem, Long cartItemId) throws UserException,Exception;

    // public CartItem isCartItemExist(Cart cart,Product product, SizeOption
    // sizeOption, Topping....., Long userid);

    public String removeCartItem(Long userId, Long cartItemId) throws UserException,Exception;

    public CartItem findCartItemById(Long cartItemId) throws Exception;

    public List<CartItem> getAllCartItems();
}
