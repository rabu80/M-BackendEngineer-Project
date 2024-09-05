package com.musinsa.api.brand.repository;

import com.musinsa.api.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByBrandName(String brandName);
    Brand getBrandByBrandId(Long brandId);
}
