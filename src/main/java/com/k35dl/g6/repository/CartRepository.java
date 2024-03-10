package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    
    @Query("SELECT c From Cart c Where c.user.id=:userId")
    public Cart findCartByUserId(@Param("userId") Long userId);
}
