package com.k35dl.g6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.repository.CartRepository;

@Service
public class CartServiceImplement implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(List<Product> products) {
        
        Cart newCart = new Cart();

        newCart.setProducts(products);

        Cart addedToCart = cartRepository.save(newCart);

        return addedToCart;
    }

    @Override
    public Cart updateCart(Cart cart, Long cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
    }

    @Override
    public String deleteCart(Long cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCart'");
    }

    @Override
    public Cart findCartById(Long cartId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCartById'");
    }

    @Override
    public List<Cart> getAllCarts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCarts'");
    }
    
}
