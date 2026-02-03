package com.kidfavor.productservice.mapper;

import com.kidfavor.productservice.dto.request.CategoryCreateRequest;
import com.kidfavor.productservice.dto.request.CategoryUpdateRequest;
import com.kidfavor.productservice.dto.response.CategoryResponse;
import com.kidfavor.productservice.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    
    public Category toEntity(CategoryCreateRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setParentId(request.getParentId());
        category.setActive(request.getActive());
        return category;
    }
    
    public void updateEntity(Category category, CategoryUpdateRequest request) {
        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        if (request.getParentId() != null) {
            category.setParentId(request.getParentId());
        }
        if (request.getActive() != null) {
            category.setActive(request.getActive());
        }
    }
    
    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParentId())
                .active(category.getActive())
                .build();
    }
    
    public List<CategoryResponse> toResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
