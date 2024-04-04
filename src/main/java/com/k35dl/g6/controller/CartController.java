package com.k35dl.g6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.CartItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.request.AddCartItemRequest;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.CartItemService;
import com.k35dl.g6.service.CartService;
import com.k35dl.g6.service.UserSerVice;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private CartItemService cartItemService;

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartItemRequest request,
            @RequestHeader("Authorization") String jwt) throws UserException, Exception {

        User user = userSerVice.findUserByJwt(jwt);

        cartService.addToCart(user.getId(), request);

        ApiResponse response = new ApiResponse();
        response.setMessage("Đã thêm vào giỏ");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/creat")
    public Cart createCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userSerVice.findUserByJwt(jwt);
        Cart cart = cartService.ceateCart(user);

        return cart;
    }

    @GetMapping
    public ResponseEntity<Cart> getUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userSerVice.findUserByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @GetMapping("/items")
    public List<CartItem> getAllCartItems() {

        return cartItemService.getAllCartItems();
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ApiResponse> removeCartItem(@RequestHeader("Authorization") String jwt,
            @PathVariable Long cartItemId) throws UserException {
        User user = userSerVice.findUserByJwt(jwt);

        cartService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Đã xóa sản phẩm khỏi giỏ hàng");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/increment/{cartItemId}")
    public ResponseEntity<CartItem> incrementCartItem(@RequestHeader("Authorization") String jwt, @PathVariable Long cartItemId) throws UserException{
        User user = userSerVice.findUserByJwt(jwt);
        CartItem cartItem = cartService.incrementCartItem(user.getId(), cartItemId);

        return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
        
    }
    @PutMapping("/decrement/{cartItemId}")
    public ResponseEntity<CartItem> decrementCartItem(@RequestHeader("Authorization") String jwt, @PathVariable Long cartItemId) throws UserException{
        User user = userSerVice.findUserByJwt(jwt);
        CartItem cartItem = cartService.decrementCartItem(user.getId(), cartItemId);

        return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
        
    }

}

