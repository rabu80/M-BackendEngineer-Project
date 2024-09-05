package com.musinsa.api.brand.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandSaveDto {

  @NotEmpty
  private String brandName;

  @Builder
  public BrandSaveDto(String brandName) {
    this.brandName = brandName;
  }

}
