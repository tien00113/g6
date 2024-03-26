package com.k35dl.g6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.UserRepository;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.UserSerVice;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSerVice userSerVice;

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserInfo(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userSerVice.findUserByJwt(jwt);

        userSerVice.updateUser(user, user.getId());

        ApiResponse response = new ApiResponse();

        response.setMessage("Đã thay đổi thông tin thành công");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userSerVice.findUserByJwt(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
