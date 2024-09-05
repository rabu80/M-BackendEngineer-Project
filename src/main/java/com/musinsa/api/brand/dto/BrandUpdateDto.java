package com.musinsa.api.brand.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandUpdateDto {

  @NotEmpty
  private String brandName;

  @Builder
  public BrandUpdateDto(String brandName) {
    this.brandName = brandName;
  }

}
