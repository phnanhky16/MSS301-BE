package com.kidfavor.productservice.mapper;

import com.kidfavor.productservice.dto.request.BrandCreateRequest;
import com.kidfavor.productservice.dto.request.BrandUpdateRequest;
import com.kidfavor.productservice.dto.response.BrandResponse;
import com.kidfavor.productservice.entity.Brand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {
    
    public Brand toEntity(BrandCreateRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setLogoUrl(request.getLogoUrl());
        brand.setActive(request.getActive());
        return brand;
    }
    
    public void updateEntity(Brand brand, BrandUpdateRequest request) {
        if (request.getName() != null) {
            brand.setName(request.getName());
        }
        if (request.getDescription() != null) {
            brand.setDescription(request.getDescription());
        }
        if (request.getLogoUrl() != null) {
            brand.setLogoUrl(request.getLogoUrl());
        }
        if (request.getActive() != null) {
            brand.setActive(request.getActive());
        }
    }
    
    public BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .active(brand.getActive())
                .build();
    }
    
    public List<BrandResponse> toResponseList(List<Brand> brands) {
        return brands.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
