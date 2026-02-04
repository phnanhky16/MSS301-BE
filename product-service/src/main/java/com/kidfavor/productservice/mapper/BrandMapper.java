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
        brand.setLogoUrl(request.getLogoUrl());
        return brand;
    }
    
    public void updateEntity(Brand brand, BrandUpdateRequest request) {
        if (request.getName() != null) {
            brand.setName(request.getName());
        }
        if (request.getLogoUrl() != null) {
            brand.setLogoUrl(request.getLogoUrl());
        }
    }
    
    public BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoUrl(brand.getLogoUrl())
                .build();
    }
    
    public List<BrandResponse> toResponseList(List<Brand> brands) {
        return brands.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
