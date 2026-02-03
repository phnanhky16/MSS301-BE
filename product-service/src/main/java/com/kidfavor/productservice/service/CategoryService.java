package com.kidfavor.productservice.service;

import com.kidfavor.productservice.dto.request.CategoryCreateRequest;
import com.kidfavor.productservice.dto.request.CategoryUpdateRequest;
import com.kidfavor.productservice.dto.response.CategoryResponse;
import com.kidfavor.productservice.entity.Category;
import com.kidfavor.productservice.mapper.CategoryMapper;
import com.kidfavor.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponseList(categories);
    }
    
    public List<CategoryResponse> getActiveCategories() {
        List<Category> categories = categoryRepository.findByActive(true);
        return categoryMapper.toResponseList(categories);
    }
    
    public Optional<CategoryResponse> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponse);
    }
    
    public Optional<CategoryResponse> getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::toResponse);
    }
    
    public List<CategoryResponse> getSubCategories(Long parentId) {
        List<Category> categories = categoryRepository.findByParentId(parentId);
        return categoryMapper.toResponseList(categories);
    }
    
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
    
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        categoryMapper.updateEntity(category, request);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
