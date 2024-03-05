package com.k35dl.g6.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.config.JwtProvider;
import com.k35dl.g6.exceptions.UserException;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.User.Role;
import com.k35dl.g6.repository.UserRepository;

@Service
public class UserServiceImplement implements UserSerVice{
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }

        throw new UserException("Người dùng không tồn tại với id "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public User updateUser(User user, Long userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if(user1.isEmpty()){
            throw new UserException("người dùng không tồn tại với id " + userId);
        }

        User oldUser = user1.get();
        if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}

        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public User setRolesUser(String adminUsername, String targetUsername, Collection<Role> roles) {
        User admin = userRepository.findByUsernameOrEmail(adminUsername, adminUsername);
        User targetUser = userRepository.findByUsernameOrEmail(targetUsername,targetUsername);

        if (admin == null || targetUser == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }

        if (!admin.getRoles().contains(User.Role.ROLE_ADMIN)) {
            throw new IllegalArgumentException("Chỉ có admin mới có quyền thay đổi roles");
        }

        if (adminUsername.equals("admin") || !targetUsername.equals("admin")) {
            targetUser.setRoles(roles);
            userRepository.save(targetUser);
        } else {
            throw new IllegalArgumentException("Chỉ có admin ban đầu mới có quyền thay đổi roles của admin khác");
        }

        return targetUser;
    }
}
