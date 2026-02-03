package com.kidfavor.productservice.service.impl;

import com.kidfavor.productservice.dto.request.BrandCreateRequest;
import com.kidfavor.productservice.dto.request.BrandUpdateRequest;
import com.kidfavor.productservice.dto.response.BrandResponse;
import com.kidfavor.productservice.entity.Brand;
import com.kidfavor.productservice.mapper.BrandMapper;
import com.kidfavor.productservice.repository.BrandRepository;
import com.kidfavor.productservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {
    
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    
    @Override
    public List<BrandResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toResponseList(brands);
    }
    
    @Override
    public List<BrandResponse> getActiveBrands() {
        List<Brand> brands = brandRepository.findByActive(true);
        return brandMapper.toResponseList(brands);
    }
    
    @Override
    public Optional<BrandResponse> getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toResponse);
    }
    
    @Override
    public Optional<BrandResponse> getBrandByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::toResponse);
    }
    
    @Override
    public BrandResponse createBrand(BrandCreateRequest request) {
        Brand brand = brandMapper.toEntity(request);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toResponse(savedBrand);
    }
    
    @Override
    public BrandResponse updateBrand(Long id, BrandUpdateRequest request) {
        Brand brand = brandRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        brandMapper.updateEntity(brand, request);
        Brand updatedBrand = brandRepository.save(brand);
        return brandMapper.toResponse(updatedBrand);
    }
    
    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
