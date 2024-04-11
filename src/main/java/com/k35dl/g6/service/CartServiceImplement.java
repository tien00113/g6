package com.k35dl.g6.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.CartItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ToppingOption;
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
    private CartItemRepository cartItemRepository;

    @Override
    public Cart ceateCart(User user) {
        Cart cart = new Cart();

        cart.setUser(user);

        return cartRepository.save(cart);
    }

    // @Override
    // public String addToCart(Long userId, AddCartItemRequest request) throws
    // UserException, Exception {
    // Cart cart = cartRepository.findCartByUserId(userId);

    // Product product = productService.findProductById(request.getProductId());

    // CartItem existingCartItem = cartItemRepository.isCartItemExist(userId,
    // request.getProductId(),
    // request.getSizeOption().getId(), request.getToppingOption().getId());

    // if (existingCartItem != null &&
    // existingCartItem.getProduct().getId().equals(product.getId())
    // &&
    // existingCartItem.getSizeOption().getId().equals(request.getSizeOption().getId())
    // &&
    // existingCartItem.getToppingOption().getId().equals(request.getToppingOption().getId()))
    // {
    // existingCartItem.setQuantity(existingCartItem.getQuantity() +
    // request.getQuantity());
    // // existingCartItem

    // cartItemRepository.save(existingCartItem);

    // } else {
    // CartItem cartItem = new CartItem();

    // cartItem.setProduct(product);
    // cartItem.setQuantity(request.getQuantity());
    // cartItem.setCart(cart);
    // cartItem.setUserId(userId);

    // int price = cartItem.getQuantity()
    // * (product.getPrice() + request.getSizeOption().getPrice() +
    // request.getToppingOption().getPrice());
    // cartItem.setPrice(price);
    // int priceSale = cartItem.getQuantity() * (product.getSalePrice() +
    // request.getSizeOption().getPrice()
    // + request.getToppingOption().getPrice());
    // cartItem.setPriceSale(priceSale);
    // cartItem.setSizeOption(request.getSizeOption());
    // cartItem.setToppingOption(request.getToppingOption());

    // CartItem createdCartItem = cartItemService.createCartItem(cartItem);
    // cart.getCartItems().add(createdCartItem);

    // // cartRepository.save(cart);
    // }

    // return "đã thêm";

    // }
    @Override
    public String addToCart(Long userId, AddCartItemRequest request) throws UserException, Exception {
        Cart cart = cartRepository.findCartByUserId(userId);

        Product product = productService.findProductById(request.getProductId());

        CartItem existingCartItem = cartItemRepository.isCartItemExist(userId, request.getProductId(),
                request.getSizeOption().getId(),
                request.getToppingOption() != null ? request.getToppingOption().getId() : null);

        if (existingCartItem != null && existingCartItem.getProduct().getId().equals(product.getId())
                && existingCartItem.getSizeOption().getId().equals(request.getSizeOption().getId())
                && (existingCartItem.getToppingOption() == null && request.getToppingOption() == null
                        || existingCartItem.getToppingOption() != null && existingCartItem.getToppingOption().getId()
                                .equals(request.getToppingOption().getId()))) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());

            cartItemRepository.save(existingCartItem);

        } else {
            CartItem cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCart(cart);
            cartItem.setUserId(userId);

            int price = cartItem.getQuantity()
                    * (product.getPrice() + request.getSizeOption().getPrice()
                            + (request.getToppingOption() != null ? request.getToppingOption().getPrice() : 0));
            cartItem.setPrice(price);
            int priceSale = cartItem.getQuantity() * (product.getSalePrice() + request.getSizeOption().getPrice()
                    + (request.getToppingOption() != null ? request.getToppingOption().getPrice() : 0));
            cartItem.setPriceSale(priceSale);
            cartItem.setSizeOption(request.getSizeOption());
            cartItem.setToppingOption(request.getToppingOption());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
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
    public String removeCartItem(Long userId, Long cartItemId) throws UserException {
        Cart cart = cartRepository.findCartByUserId(userId);

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        if (cartItem == null || !cartItem.getUserId().equals(userId)) {
            throw new UserException("Không tìm thấy sản phẩm trong giỏ hàng");
        }

        cart.getCartItems().remove(cartItem);

        cartItemRepository.delete(cartItem);

        return "đã xóa sản phẩm khỏi giỏ hàng";
    }

    @Override
    public CartItem incrementCartItem(Long userId, Long cartItemId) throws UserException {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        if (cartItem != null && cartItem.getUserId().equals(userId)) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice((cartItem.getProduct().getPrice() + cartItem.getSizeOption().getPrice()
                    + ((cartItem.getToppingOption() != null) ? cartItem.getToppingOption().getPrice() : 0))
                    * cartItem.getQuantity());
            cartItem.setPriceSale((cartItem.getProduct().getSalePrice() + cartItem.getSizeOption().getPrice()
                    + ((cartItem.getToppingOption() != null) ? cartItem.getToppingOption().getPrice() : 0))
                    * cartItem.getQuantity());

            cartItemRepository.save(cartItem);
        } else {
            throw new UserException("Không tìm thấy sản phẩm trong giỏ hoặc lỗi user");
        }

        return cartItem;
    }

    @Override
    public CartItem decrementCartItem(Long userId, Long cartItemId) throws UserException {
        Cart cart = cartRepository.findCartByUserId(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        if (cartItem != null && cartItem.getUserId().equals(userId)) {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItem.setPrice((cartItem.getProduct().getPrice() + cartItem.getSizeOption().getPrice()
                        + ((cartItem.getToppingOption() != null) ? cartItem.getToppingOption().getPrice() : 0))
                        * cartItem.getQuantity());
                cartItem.setPriceSale((cartItem.getProduct().getSalePrice() + cartItem.getSizeOption().getPrice()
                        + ((cartItem.getToppingOption() != null) ? cartItem.getToppingOption().getPrice() : 0))
                        * cartItem.getQuantity());
                cartItemRepository.save(cartItem);
            } else {
                cart.getCartItems().remove(cartItem);
                cartItemRepository.delete(cartItem);
                cartItem = null;
            }
        } else {
            throw new UserException("Không tìm thấy sản phẩm trong giỏ hoặc lỗi user");
        }

        return cartItem;
    }

}
