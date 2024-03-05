package com.k35dl.g6.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsernameOrEmail("admintest", "admintest") == null) {
            User admin = new User();
            admin.setUsername("admintest");

            admin.setPassword(passwordEncoder.encode("12345678"));

            admin.setFirstName("admintest");

            admin.setLastName("promax");

            admin.setEmail("adminpromax@gmail.com");

            admin.setRoles(Collections.singleton(User.Role.ROLE_ADMIN));

            userRepository.save(admin);
        }

    }

}
