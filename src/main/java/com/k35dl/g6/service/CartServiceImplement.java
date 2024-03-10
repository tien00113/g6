package com.k35dl.g6.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.CartItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.repository.CartItemRepository;
import com.k35dl.g6.repository.CartRepository;
import com.k35dl.g6.request.AddCartItemRequest;
import com.k35dl.g6.service.Product.ProductService;

@Service
public class CartServiceImplement implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart ceateCart(User user) {
        Cart cart = new Cart();

        cart.setUser(user);

        return cartRepository.save(cart);
    }

    @Override
    public String addToCart(Long userId, AddCartItemRequest request) throws UserException, Exception {
        Cart cart = cartRepository.findCartByUserId(userId);

        Product product = productService.findProductById(request.getProductId());

        CartItem existingCartItem = cartItemRepository.isCartItemExist(userId, request.getProductId());

        if (existingCartItem != null && existingCartItem.getProduct().getId().equals(product.getId())) {
            throw new Exception("Sản phẩm đã có trong giỏ hàng");
        } else {
            CartItem cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCart(cart);
            cartItem.setUserId(userId);

            int price = request.getQuantity() * product.getPrice();
            cartItem.setPrice(price);
            int priceSale = request.getQuantity() * product.getSalePrice();
            cartItem.setPriceSale(priceSale);
            cartItem.setSizeOption(request.getSizeOption());
            cartItem.setToppingOptions(request.getToppingOption());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);

            // cartRepository.save(cart);
        }

        return "đã thêm";

    }

    @Override
    public Cart findCartById(Long cartId) throws Exception {

        Optional<Cart> opt = cartRepository.findById(cartId);
        if (opt.isEmpty()) {
            throw new Exception("Không tìm thấy giỏ hàng");
        }

        return opt.get();
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);

        int totalPrice = 0;
        int totalSalePrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice + cartItem.getPrice();
            totalSalePrice = totalSalePrice + cartItem.getPriceSale();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalSalePrice(totalSalePrice);
        cart.setTotalItem(totalItem);

        return cartRepository.save(cart);
    }

    @Override
    public Cart testCart() throws ProductException {
        // CartItem cartItem = new CartItem();

        Cart cart = cartService.findUserCart((long) 1);

        CartItem cartItem = new CartItem();

        cartItem.setQuantity(19);
        cartItem.setPrice(1923234);

        // Product product = productService.findProductById((long) 1);

        cartItem.setCart(cart);
        // cartItem.setPrice(928283);
        // cartItem.setPriceSale(236434);
        // cartItem.setProduct(product);
        // cartItem.setQuantity(3);
        // // cartItem.setSizeOption(sizeOption);
        // // cartItem.set
        // cartItem.setUserId((long) 1);

        // cart.getCartItems().add(createdCartItem);
        // cart.setTotalItem(2);
        // cart.setTotalPrice(1090);
        // cart.setTotalSalePrice(165);
        cart.setTotalPrice(127327);
        cart.getCartItems().add(cartItem);

        return cartRepository.save(cart);
    }

}
