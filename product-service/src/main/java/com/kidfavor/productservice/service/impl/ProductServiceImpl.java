package com.kidfavor.productservice.service.impl;

import com.kidfavor.productservice.dto.request.ProductCreateRequest;
import com.kidfavor.productservice.dto.request.ProductUpdateRequest;
import com.kidfavor.productservice.dto.request.StatusUpdateRequest;
import com.kidfavor.productservice.dto.response.ProductResponse;
import com.kidfavor.productservice.entity.Brand;
import com.kidfavor.productservice.entity.Category;
import com.kidfavor.productservice.entity.Product;
import com.kidfavor.productservice.enums.EntityStatus;
import com.kidfavor.productservice.exception.ResourceNotFoundException;
import com.kidfavor.productservice.mapper.ProductMapper;
import com.kidfavor.productservice.repository.BrandRepository;
import com.kidfavor.productservice.repository.CategoryRepository;
import com.kidfavor.productservice.repository.ProductRepository;
import com.kidfavor.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;
    
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponseList(products);
    }
    
    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .map(productMapper::toResponse);
    }
    
    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryIdAndStatus(categoryId, EntityStatus.ACTIVE);
        return productMapper.toResponseList(products);
    }
    
    @Override
    public List<ProductResponse> getProductsByBrand(Long brandId) {
        List<Product> products = productRepository.findByBrandIdAndStatus(brandId, EntityStatus.ACTIVE);
        return productMapper.toResponseList(products);
    }
    
    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        List<Product> products = productRepository.searchByNameAndStatus(keyword, EntityStatus.ACTIVE);
        return productMapper.toResponseList(products);
    }
    
    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Category category = categoryRepository.findByIdAndStatus(request.getCategoryId(), EntityStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        
        Brand brand = brandRepository.findByIdAndStatus(request.getBrandId(), EntityStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + request.getBrandId()));
        
        Product product = productMapper.toEntity(request, category, brand);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }
    
    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findByIdAndStatus(request.getCategoryId(), EntityStatus.ACTIVE)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        }
        
        Brand brand = null;
        if (request.getBrandId() != null) {
            brand = brandRepository.findByIdAndStatus(request.getBrandId(), EntityStatus.ACTIVE)
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + request.getBrandId()));
        }
        
        productMapper.updateEntity(product, request, category, brand);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);
    }
    
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        product.setStatus(EntityStatus.DELETED);
        product.setStatusChangedAt(LocalDateTime.now());
        productRepository.save(product);
    }
    
    @Override
    public ProductResponse updateProductStatus(Long id, StatusUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        product.setStatus(request.getStatus());
        product.setStatusChangedAt(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);
    }
}
