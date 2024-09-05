package com.musinsa.api.codi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BrandCategoryPrcDto {

    private String categoryName;
    private String brandName;
    private Long itemPrice;

    @Builder
    public BrandCategoryPrcDto(String categoryName, String brandName, Long itemPrice) {
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.itemPrice = itemPrice;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BrandCategoryLowestPrcResponseDto {
        private String 카테고리;
        private String 브랜드;
        private String 가격;

        @Builder
        public BrandCategoryLowestPrcResponseDto(String categoryName, String brandName, String itemPrice) {
            this.카테고리 = categoryName;
            this.브랜드 = brandName;
            this.가격 = itemPrice;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LowestPrcItemsByBrandResponseDto {
        private String 총액;
        private List<BrandCategoryLowestPrcResponseDto> itemList;

        @Builder
        public LowestPrcItemsByBrandResponseDto(String totalPrice, List<BrandCategoryLowestPrcResponseDto> itemList) {
            this.총액 = totalPrice;
            this.itemList = itemList;
        }
    }
}
