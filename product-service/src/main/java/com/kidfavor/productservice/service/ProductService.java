package com.kidfavor.productservice.service;

import com.kidfavor.productservice.dto.request.ProductCreateRequest;
import com.kidfavor.productservice.dto.request.ProductUpdateRequest;
import com.kidfavor.productservice.dto.request.StatusUpdateRequest;
import com.kidfavor.productservice.dto.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    List<ProductResponse> getAllProducts();
    
    Optional<ProductResponse> getProductById(Long id);
    
    List<ProductResponse> getProductsByCategory(Long categoryId);
    
    List<ProductResponse> getProductsByBrand(Long brandId);
    
    List<ProductResponse> searchProducts(String keyword);
    
    ProductResponse createProduct(ProductCreateRequest request);
    
    ProductResponse updateProduct(Long id, ProductUpdateRequest request);
    
    void deleteProduct(Long id);
    
    ProductResponse updateProductStatus(Long id, StatusUpdateRequest request);
}
