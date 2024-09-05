package com.musinsa.api.brand.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDto {

  private Long brandId;
  private String brandName;

  @Builder
  public BrandDto(Long brandId, String brandName) {
    this.brandId = brandId;
    this.brandName = brandName;
  }

}
