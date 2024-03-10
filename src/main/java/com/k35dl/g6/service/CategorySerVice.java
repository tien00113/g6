package com.k35dl.g6.service;

import java.util.List;

import com.k35dl.g6.models.Category;

public interface CategoryService {
    public Category createCategory(Category category) throws Exception;

    public Category updateCategory(Category category, Long categoryId) throws Exception;

    public String deleteCategory(Long categoryId) throws Exception;

    public Category findCategoryById(Long categoryId) throws Exception;

    public List<Category> getAllCategory();

}
