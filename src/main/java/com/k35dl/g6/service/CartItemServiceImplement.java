package com.k35dl.g6.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.CartItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.CartItemRepository;

@Service
public class CartItemServiceImplement implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserSerVice userSerVice;

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartitem(Long userId, CartItem cartItem, Long cartItemId) throws Exception, UserException {

        CartItem item = findCartItemById(cartItemId);

        User user = userSerVice.findUserById(item.getUserId());

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setPriceSale(item.getQuantity() * item.getProduct().getSalePrice());
            item.setSizeOption(cartItem.getSizeOption());
            item.setToppingOption(cartItem.getToppingOption());
        }

        return cartItemRepository.save(item);

    }

    @Override
    public String removeCartItem(Long userId, Long cartItemId) throws UserException, Exception {
        CartItem cartItem = findCartItemById(cartItemId);

        User user = userSerVice.findUserById(cartItem.getUserId());

        User reqUser = userSerVice.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new Exception("Không thể xóa item của user khác");
        }
        return "Đã xóa thành công";
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws Exception {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new Exception("Khong tim thay cartitem co id " + cartItemId);
        }

    }

    @Override
    public List<CartItem> getAllCartItems() {

        return cartItemRepository.findAll();
    }

}
