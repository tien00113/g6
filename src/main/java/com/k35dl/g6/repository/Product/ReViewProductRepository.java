package com.k35dl.g6.repository.Product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ReViewProduct;

public interface ReViewProductRepository extends JpaRepository<ReViewProduct, Long>{
    public List<ReViewProduct> findByProduct(Product product);

    @Modifying
    @Transactional
    @Query("update Order o set o.updateStatusAt = :updateStatusAt where o.id = :id")
    void updateOrderStatus(@Param("id") Long id, @Param("updateStatusAt") LocalDateTime updateStatusAt);
}
