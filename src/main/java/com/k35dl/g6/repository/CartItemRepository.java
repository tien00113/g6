package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
    @Query("SELECT ci From CartItem ci Where ci.userId=:userId AND ci.product.id=:productId")
    public CartItem isCartItemExist(@Param("userId") Long userId, @Param("productId") Long productId);
}
