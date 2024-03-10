package com.k35dl.g6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.Category;
import com.k35dl.g6.service.CategoryService;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categorySerVice;

    @PostMapping("/api/categorys")
    public Category creatCategory(@RequestBody Category category) throws Exception{

        Category createdCategory = categorySerVice.createCategory(category);

        return createdCategory;
    }

    @PutMapping("/api/categorys/{categoryId}")
    public Category updatCategory(@RequestBody Category category, @PathVariable Long categoryId) throws Exception{

        Category updatedCategory = categorySerVice.updateCategory(category, categoryId);

        return updatedCategory;
    }

    @DeleteMapping("/api/categorys/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) throws Exception{

        String message = categorySerVice.deleteCategory(categoryId);

        return message;
    }
}
