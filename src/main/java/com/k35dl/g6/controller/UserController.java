package com.k35dl.g6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.repository.UserRepository;
import com.k35dl.g6.service.UserSerVice;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSerVice userSerVice;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
}
