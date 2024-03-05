package com.k35dl.g6.service;

import java.util.Collection;

import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.User;

public interface UserSerVice {
    public User registerUser(User user);
    
    public User findUserById(Long userId) throws UserException;

    public User findUserByEmail(String email);

    public User findUserByJwt(String jwt);

    public User updateUser(User user, Long userId) throws UserException;

    public User setRolesUser(String adminUsername, String targetUsername, Collection<User.Role> roles);

}
