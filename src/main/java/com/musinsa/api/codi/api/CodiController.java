package com.musinsa.api.codi.api;

import com.musinsa.api.codi.dto.BrandCategoryPrcDto;
import com.musinsa.api.codi.dto.BrandPrcDto;
import com.musinsa.api.codi.dto.CategoryPrcDto;
import com.musinsa.api.codi.service.CodiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodiController {

    private final CodiService codiService;

    // 의존성 주입을 위한 생성자
    public CodiController(CodiService codiService) {
        this.codiService = codiService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/brandCategoryLowestPrices")
    @Operation(summary = "구현과제: 1번", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    BrandCategoryPrcDto.LowestPrcItemsByBrandResponseDto getLowestPriceBrandAndCategory() {
        return codiService.getLowestPriceBrandAndCategory();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/lowestPriceBrandItems")
    @Operation(summary = "구현과제: 2번", description =
            "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API")
    CategoryPrcDto.LowestPrcCategoryByBrandReponseDto getLowestPriceBrandItems() {
        return codiService.getLowestPriceBrandItems();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/lowHighPriceBrandItemsByCategory/{categoryName}")
    @Operation(summary = "구현과제: 3번", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")
    BrandPrcDto.LowestPrcBrandByCategoryResponseDto getLowHighPriceBrandItemsByCategory(@PathVariable String categoryName) {
        return codiService.getLowHighPriceBrandItemsByCategory(categoryName);
    }



}
