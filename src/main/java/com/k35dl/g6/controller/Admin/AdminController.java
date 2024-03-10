package com.k35dl.g6.controller.Admin;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.User;
import com.k35dl.g6.models.User.Role;
import com.k35dl.g6.service.UserSerVice;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserSerVice userSerVice;
    
    @PutMapping("/user/setrole")
    public User adminSetRoleUser(String adminUsername, String targetUsername, Collection<Role> roles){

       User user =  userSerVice.setRolesUser(adminUsername, targetUsername, roles);

        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userSerVice.getAllusers();
    }

}
