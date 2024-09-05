package com.musinsa.api.codi.service;

import com.musinsa.api.brand.domain.Brand;
import com.musinsa.api.brand.repository.BrandRepository;
import com.musinsa.api.codi.dto.BrandCategoryPrcDto;
import com.musinsa.api.codi.dto.BrandPrcDto;
import com.musinsa.api.codi.dto.CategoryPrcDto;
import com.musinsa.api.item.domain.Item;
import com.musinsa.api.item.dto.BrandItemInterface;
import com.musinsa.api.item.dto.ItemDto;
import com.musinsa.api.item.exception.CategoryNotFoundException;
import com.musinsa.api.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.musinsa.api.global.util.GlobalUtils.getNumberCommaFormat;
import static com.musinsa.api.global.util.GlobalUtils.isExistsCategoryName;

@Service
public class CodiService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;

    public CodiService(ItemRepository itemRepository, BrandRepository brandRepository) {
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
    }

    // 브랜드 Map 작성 (브랜드 명 구하는 용도)
    private Map<Long, String> getBrandInfoMap() {
        List<Brand> brandList = brandRepository.findAll();
        return brandList.stream()
                .collect(Collectors.toMap(Brand::getBrandId, Brand::getBrandName));
    }

    // Item EntityList를 DtoList로 변환
    private List<ItemDto> convertItemEntityToDtoList(List<Item> itemList) {

        return itemList.stream()
                .map(item -> ItemDto.builder()
                .itemPrice(item.getItemPrice())
                .categoryName(item.getCategoryName())
                .brandId(item.getBrandId())
                .build())
                .toList();
    }

    /*
        구현 1번
        - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     */
    public BrandCategoryPrcDto.LowestPrcItemsByBrandResponseDto getLowestPriceBrandAndCategory() {

        List<Item> itemList = itemRepository.findAll();
        List<ItemDto> dtoList = convertItemEntityToDtoList(itemList);
        List<BrandCategoryPrcDto> categoryBrandLowestPriceList = makeCategoryBrandLwstPriceList(dtoList);

        Long totalPrice = categoryBrandLowestPriceList.stream()
                .mapToLong(BrandCategoryPrcDto::getItemPrice)
                .sum();

        List<BrandCategoryPrcDto.BrandCategoryLowestPrcResponseDto> categoryBrandList = categoryBrandLowestPriceList.stream()
                .map(c -> BrandCategoryPrcDto.BrandCategoryLowestPrcResponseDto.builder()
                        .categoryName(c.getCategoryName())
                        .brandName(c.getBrandName())
                        .itemPrice(getNumberCommaFormat(c.getItemPrice()))
                        .build())
                .toList();

        return BrandCategoryPrcDto.LowestPrcItemsByBrandResponseDto.builder()
                .totalPrice(getNumberCommaFormat(totalPrice))
                .itemList(categoryBrandList)
                .build();
    }

    // 구현1번 카테고리별 최저가격 브랜드/가격정보 정렬
    private List<BrandCategoryPrcDto> makeCategoryBrandLwstPriceList (List<ItemDto> itemList) {

        Map<Long, String> brandMap = getBrandInfoMap();

        Map<String, List<ItemDto>> itemByCategoryGroup = itemByCategoryGrouping(itemList);
        List<BrandCategoryPrcDto> categoryBrandLowestPriceList = new ArrayList<>();
        for(String key : itemByCategoryGroup.keySet()) {
            List<ItemDto> tmpList = itemByCategoryGroup.get(key);
            tmpList.stream().min(Comparator.comparingLong(ItemDto::getItemPrice))
                    .map(p -> categoryBrandLowestPriceList.add(BrandCategoryPrcDto.builder()
                            .brandName(brandMap.get(p.getBrandId()))
                            .categoryName(key)
                            .itemPrice(p.getItemPrice())
                            .build()));
        }

        return categoryBrandLowestPriceList;
    }

    // 카테고리별 상품정보 grouping
    private Map<String, List<ItemDto>> itemByCategoryGrouping(List<ItemDto> itemList) {
        return itemList.stream()
                .collect(Collectors.groupingBy(ItemDto::getCategoryName));
    }

    // 카테고리별 합산금액 최저가 브랜드ID
    private Long getLowestPriceBrandId(List<BrandItemInterface> brandList) {
        return brandList.stream()
                .min(Comparator.comparingLong(BrandItemInterface::getItemPriceSum))
                .map(BrandItemInterface::getBrandId)
                .get();
    }

    // 구헌 2번 브랜드 기준 카테고리별 최저가 계산
    private List<CategoryPrcDto> makeCategoryLwstPriceList(List<ItemDto> itemList) {
        Map<String, List<ItemDto>> itemByCategoryGroup = itemByCategoryGrouping(itemList);
        List<CategoryPrcDto> categoryLwstPriceList = new ArrayList<>();
        for(String key : itemByCategoryGroup.keySet()) {
            List<ItemDto> tmpList = itemByCategoryGroup.get(key);
            tmpList.stream().min(Comparator.comparingLong(ItemDto::getItemPrice))
                    .map(p -> categoryLwstPriceList.add(CategoryPrcDto.builder()
                            .categoryName(key)
                            .itemPrice(p.getItemPrice())
                            .build()));
        }

        return categoryLwstPriceList;
    }

    /*
        구현 2번
        - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
     */
    public CategoryPrcDto.LowestPrcCategoryByBrandReponseDto getLowestPriceBrandItems() {

        Map<Long, String> brandMap = getBrandInfoMap();
        Long lowestPriceBrandId = getLowestPriceBrandId(itemRepository.findBrandItemPriceSum().stream().toList());
        List<ItemDto> itemList = convertItemEntityToDtoList(itemRepository.findByBrandId(lowestPriceBrandId));
        List<CategoryPrcDto> categoryLwstPriceList = makeCategoryLwstPriceList(itemList);

        Long totalPrice = categoryLwstPriceList.stream()
                .mapToLong(CategoryPrcDto::getItemPrice)
                .sum();

       List<CategoryPrcDto.CategoryPrcResponseDto> categoryList = categoryLwstPriceList.stream()
                .map(c -> CategoryPrcDto.CategoryPrcResponseDto.builder()
                        .categoryName(c.getCategoryName())
                        .itemPrice(getNumberCommaFormat(c.getItemPrice()))
                        .build())
                .toList();

        return CategoryPrcDto.LowestPrcCategoryByBrandReponseDto.builder()
                .totalPrice(getNumberCommaFormat(totalPrice))
                .brandName(brandMap.get(lowestPriceBrandId))
                .categoryPrcList(categoryList)
                .build();
    }

    /*
        구현 3번
        - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
     */
    public BrandPrcDto.LowestPrcBrandByCategoryResponseDto getLowHighPriceBrandItemsByCategory(String categoryName) {

        if(!isExistsCategoryName(categoryName)) {
            throw new CategoryNotFoundException();
        }

        Map<Long, String> brandMap = getBrandInfoMap();
        List<ItemDto> itemList = convertItemEntityToDtoList(itemRepository.findByCategoryName(categoryName));
        BrandPrcDto.BrandPrcResponseDto minPrcDto = itemList.stream()
                .min(Comparator.comparingLong(ItemDto::getItemPrice))
                .map(b -> BrandPrcDto.BrandPrcResponseDto.builder()
                        .brandName(brandMap.get(b.getBrandId()))
                        .itemPrice(getNumberCommaFormat(b.getItemPrice()))
                        .build()).get();

        BrandPrcDto.BrandPrcResponseDto maxPrcDto = itemList.stream()
                .max(Comparator.comparingLong(ItemDto::getItemPrice))
                .map(b -> BrandPrcDto.BrandPrcResponseDto.builder()
                        .brandName(brandMap.get(b.getBrandId()))
                        .itemPrice(getNumberCommaFormat(b.getItemPrice()))
                        .build()).get();

        return BrandPrcDto.LowestPrcBrandByCategoryResponseDto.builder()
                .categoryName(categoryName)
                .lowestPrcBrandDto(minPrcDto)
                .maximumPrcBrandDto(maxPrcDto)
                .build();
    }


}
