package com.musinsa.api.codi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandPrcDto {

  private String brandName;
  private Long itemPrice;

  @Builder
  public BrandPrcDto(String brandName, Long itemPrice) {
    this.brandName = brandName;
    this.itemPrice = itemPrice;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class BrandPrcResponseDto {
    private String 브랜드;
    private String 가격;

    @Builder
    public BrandPrcResponseDto(String brandName, String itemPrice) {
      this.브랜드 = brandName;
      this.가격 = itemPrice;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class LowestPrcBrandByCategoryResponseDto {

    private String 카테고리;
    private BrandPrcResponseDto 최저가;
    private BrandPrcResponseDto 최고가;

    @Builder
    public LowestPrcBrandByCategoryResponseDto(String categoryName,
                                     BrandPrcResponseDto lowestPrcBrandDto,
                                     BrandPrcResponseDto maximumPrcBrandDto) {
      this.카테고리 = categoryName;
      this.최저가 = lowestPrcBrandDto;
      this.최고가 = maximumPrcBrandDto;
    }
  }

}
