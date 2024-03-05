package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
