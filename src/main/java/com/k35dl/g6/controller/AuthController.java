package com.k35dl.g6.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.config.JwtProvider;
import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.UserRepository;
import com.k35dl.g6.request.LoginRequest;
import com.k35dl.g6.response.AuthResponse;
import com.k35dl.g6.service.CartService;
import com.k35dl.g6.service.CustomerUserDetailsService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartService cartService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("Email này đã được sử dụng bởi tài khoản khác");
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

    @PostMapping("/signin")
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
    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        return "redirect:/signin";
    }
}
