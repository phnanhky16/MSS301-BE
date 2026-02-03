package com.kidfavor.productservice.service;

import com.kidfavor.productservice.dto.request.CategoryCreateRequest;
import com.kidfavor.productservice.dto.request.CategoryUpdateRequest;
import com.kidfavor.productservice.dto.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    
    List<CategoryResponse> getAllCategories();
    
    List<CategoryResponse> getActiveCategories();
    
    Optional<CategoryResponse> getCategoryById(Long id);
    
    Optional<CategoryResponse> getCategoryByName(String name);
    
    List<CategoryResponse> getSubCategories(Long parentId);
    
    CategoryResponse createCategory(CategoryCreateRequest request);
    
    CategoryResponse updateCategory(Long id, CategoryUpdateRequest request);
    
    void deleteCategory(Long id);
}


