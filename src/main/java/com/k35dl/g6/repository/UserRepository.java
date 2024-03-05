package com.k35dl.g6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);

    public User findByUsernameOrEmail(String username, String email);
}
