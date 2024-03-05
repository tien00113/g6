package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    
}
