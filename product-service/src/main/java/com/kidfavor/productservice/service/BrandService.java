package com.kidfavor.productservice.service;

import com.kidfavor.productservice.entity.Brand;
import com.kidfavor.productservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    
    private final BrandRepository brandRepository;
    
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
    
    public List<Brand> getActiveBrands() {
        return brandRepository.findByActive(true);
    }
    
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }
    
    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }
    
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }
    
    public Brand updateBrand(Long id, Brand brandDetails) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        brand.setName(brandDetails.getName());
        brand.setDescription(brandDetails.getDescription());
        brand.setLogoUrl(brandDetails.getLogoUrl());
        brand.setActive(brandDetails.getActive());
        
        return brandRepository.save(brand);
    }
    
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
