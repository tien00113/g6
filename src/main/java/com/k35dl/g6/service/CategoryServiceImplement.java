package com.k35dl.g6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Category;
import com.k35dl.g6.repository.CategoryRepository;

@Service
public class CategoryServiceImplement implements CategorySerVice {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) throws Exception {
        Category newCategory = new Category();

        newCategory.setName(category.getName());

        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) throws Exception {

        Optional<Category> category1 = categoryRepository.findById(categoryId);

        if (category1.isEmpty()) {
            throw new Exception("Không tìm thấy phân loại có id " + categoryId);
        }

        Category oldCategory = category1.get();

        if(category.getName()!= null){
            oldCategory.setName(category.getName());
        }

        Category updatedCategory = categoryRepository.save(oldCategory);

        return updatedCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) throws Exception {
        
        Category category = findCategoryById(categoryId);

        categoryRepository.delete(category);

        return "Đã xóa thành công";
    }

    @Override
    public Category findCategoryById(Long categoryId) throws Exception {
        
        Optional<Category> opt = categoryRepository.findById(categoryId);

        if(opt.isEmpty()){
            throw new Exception("Không tìm thấy phân loại có id "+categoryId);
        }

        return opt.get();
    }

    @Override
    public List<Category> getAllCategory() {
        
        return categoryRepository.findAll();
    }

}
