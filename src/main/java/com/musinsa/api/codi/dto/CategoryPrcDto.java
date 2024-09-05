package com.musinsa.api.codi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryPrcDto {

  private String categoryName;
  private Long itemPrice;

  @Builder
  public CategoryPrcDto(String categoryName, Long itemPrice) {
    this.categoryName = categoryName;
    this.itemPrice = itemPrice;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class CategoryPrcResponseDto {
    private String 카테고리;
    private String 가격;

    @Builder
    public CategoryPrcResponseDto(String categoryName, String itemPrice) {
      this.카테고리 = categoryName;
      this.가격 = itemPrice;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class LowestPrcCategoryByBrandReponseDto {

    private String 브랜드;
    private List<CategoryPrcResponseDto> 카테고리;
    private String 총액;

    @Builder
    public LowestPrcCategoryByBrandReponseDto(String brandName,
                                              List<CategoryPrcResponseDto> categoryPrcList,
                                              String totalPrice) {
      this.브랜드 = brandName;
      this.카테고리 = categoryPrcList;
      this.총액 = totalPrice;
    }
  }

}
