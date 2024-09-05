package com.musinsa.api.brand.api;

import com.musinsa.api.brand.dto.BrandDto;
import com.musinsa.api.brand.dto.BrandSaveDto;
import com.musinsa.api.brand.dto.BrandUpdateDto;
import com.musinsa.api.brand.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {

    private final BrandService brandService;

    // 의존성 주입을 위한 생성자
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/brands")
    @Operation(summary = "브랜드등록 API", description = "브랜드정보를 입력한다.")
    void saveBrand(@RequestBody BrandSaveDto brandSaveDto) throws Exception {
        brandService.saveBrand(brandSaveDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("api/v1/brands/{brandId}")
    @Operation(summary = "브랜드수정 API", description = "브랜드정보를 수정한다.")
    public void updateBrand(@PathVariable("brandId") Long brandId,
                              @Valid @RequestBody BrandUpdateDto brandUpdateDto) throws Exception {
        brandService.updateBrand(brandId, brandUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("api/v1/brands/{brandId}")
    @Operation(summary = "브랜드삭제 API", description = "브랜드정보를 삭제한다.")
    public void deleteProduct(@PathVariable("brandId") Long brandId) throws Exception {
        brandService.deleteBrand(brandId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/brands")
    @Operation(summary = "전체 브랜드조회 API", description = "전체 브랜드정보를 조회한다.")
    List<BrandDto> brandList() {
        return brandService.getAllBrands();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/brands/{brandId}")
    @Operation(summary = "브랜드ID를 이용한 브랜드조회 API", description = "브랜드ID기준 브랜드 1건을 조회한다.(Unique)")
    BrandDto getBrandByBrandId(@PathVariable("brandId") Long brandId) {
        return brandService.getBrandByBrandId(brandId);
    }

}
