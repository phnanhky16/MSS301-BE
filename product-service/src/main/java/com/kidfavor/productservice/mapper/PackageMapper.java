package com.kidfavor.productservice.mapper;

import com.kidfavor.productservice.dto.request.PackageCreateRequest;
import com.kidfavor.productservice.dto.request.PackageUpdateRequest;
import com.kidfavor.productservice.dto.response.PackageResponse;
import com.kidfavor.productservice.entity.Package;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PackageMapper {
    
    public Package toEntity(PackageCreateRequest request) {
        Package pkg = new Package();
        pkg.setName(request.getName());
        pkg.setDescription(request.getDescription());
        pkg.setPrice(request.getPrice());
        pkg.setActive(request.getActive());
        return pkg;
    }
    
    public void updateEntity(Package pkg, PackageUpdateRequest request) {
        if (request.getName() != null) {
            pkg.setName(request.getName());
        }
        if (request.getDescription() != null) {
            pkg.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            pkg.setPrice(request.getPrice());
        }
        if (request.getActive() != null) {
            pkg.setActive(request.getActive());
        }
    }
    
    public PackageResponse toResponse(Package pkg) {
        return PackageResponse.builder()
                .id(pkg.getId())
                .name(pkg.getName())
                .description(pkg.getDescription())
                .price(pkg.getPrice())
                .active(pkg.getActive())
                .build();
    }
    
    public List<PackageResponse> toResponseList(List<Package> packages) {
        return packages.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
