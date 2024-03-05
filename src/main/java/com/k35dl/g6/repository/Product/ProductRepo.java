package com.k35dl.g6.repository.Product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Product.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
    
}
