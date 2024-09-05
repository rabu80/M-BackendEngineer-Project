package com.musinsa.api.brand.domain;

import com.musinsa.api.brand.dto.BrandUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "idx_brand_01", columnList = "brand_name"))
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long brandId;

    @Column(length = 100, nullable = false)
    private String brandName;

    @Builder
    public Brand(Long brandId, String brandName) {
        this.brandName = brandName;
    }

    public void update(BrandUpdateDto brandUpdateDto){
        this.brandName = brandUpdateDto.getBrandName();
    }
}
