package com.k35dl.g6.repository.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ReViewProduct;

public interface ReViewProductRepository extends JpaRepository<ReViewProduct, Long>{
    public List<ReViewProduct> findByProduct(Product product);
}
