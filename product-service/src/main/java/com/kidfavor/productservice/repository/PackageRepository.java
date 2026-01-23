package com.kidfavor.productservice.repository;

import com.kidfavor.productservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByActive(Boolean active);
}
