package com.k35dl.g6.repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k35dl.g6.models.Product.ToppingOption;

public interface ToppingOptionRepository extends JpaRepository<ToppingOption, Long>{
    
}
