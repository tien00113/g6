package com.k35dl.g6.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.config.JwtProvider;
import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.repository.UserRepository;
import com.k35dl.g6.request.LoginRequest;
import com.k35dl.g6.response.AuthResponse;
import com.k35dl.g6.service.CartService;
import com.k35dl.g6.service.CustomerUserDetailsService;
import com.k35dl.g6.service.Product.ProductService;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/auth/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (isExist != null) {
            throw new Exception("Email hoặc username này đã được sử dụng bởi tài khoản khác");
        }

        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        // mặc định tài khoản đăng ký mới là role user
        newUser.setRoles(Collections.singleton(User.Role.valueOf("USER")));

        User savedUser = userRepository.save(newUser);

        cartService.ceateCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, "Đăng ký thành công");

        return response;
    }

    @PostMapping("/auth/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticate(loginRequest.getEmail(),
                loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, "Đăng nhập thành công");

        return response;
    }

    private Authentication authenticate(String usernameOrEmail, String password) {

        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(usernameOrEmail);

        if (userDetails == null) {
            throw new BadCredentialsException("username hoặc email sử dụng không hợp lệ");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    // cấu hình logout đơn giản:: token vẫn còn hiệu lực nếu chưa hết hạn-> hủy hiệu
    // lực của token khi đăng xuất cần cấu hình phức tạp, giảm hiệu suất//
    @GetMapping("/auth/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "đăng xuất";
    }

    @GetMapping("/allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.ACCEPTED);
    }

    @GetMapping("/allproduct/{productId}")
    public ResponseEntity<Product> getProductDetail(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/allproduct/cart")
    public ResponseEntity<List<Product>> getProductInCart(@RequestBody List<Long> productIds)
            throws ProductException {
        List<Product> products = productService.findProductByListId(productIds);

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}
