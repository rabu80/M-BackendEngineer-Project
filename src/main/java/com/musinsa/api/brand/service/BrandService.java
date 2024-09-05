package com.musinsa.api.brand.service;

import com.musinsa.api.brand.domain.Brand;
import com.musinsa.api.brand.dto.BrandDto;
import com.musinsa.api.brand.dto.BrandSaveDto;
import com.musinsa.api.brand.dto.BrandUpdateDto;
import com.musinsa.api.brand.excpetion.BrandNotFoundException;
import com.musinsa.api.brand.excpetion.DuplicateBrandException;
import com.musinsa.api.brand.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandService(ModelMapper modelMapper, ModelMapper modelMapper1, BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper1;
    }

    @Transactional
    public Brand saveBrand(BrandSaveDto brandSaveDto) {

        if(isExistsBrandName(brandSaveDto.getBrandName())) {
            throw new DuplicateBrandException();
        }

        Brand brand = Brand.builder()
                .brandName(brandSaveDto.getBrandName())
                .build();
        return brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(Long brandId, BrandUpdateDto brandUpdateDto) {
        Brand savedBrand = brandRepository.findById(brandId)
                .orElseThrow(BrandNotFoundException::new);
        savedBrand.update(brandUpdateDto);
        brandRepository.save(savedBrand);
    }

    @Transactional
    public void deleteBrand(Long brandId) {
        if(!isExistsBrandId(brandId)) {
            throw new BrandNotFoundException();
        }
        brandRepository.deleteById(brandId);
    }

    @Transactional(readOnly = true)
    public List<BrandDto> getAllBrands() {
        List<Brand> brandList = brandRepository.findAll();
        return brandList.stream()
                .map(entity -> modelMapper.map(entity, BrandDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BrandDto getBrandByBrandId(Long brandId) {
        if(!isExistsBrandId(brandId)) {
            throw new BrandNotFoundException();
        }
        return modelMapper.map(brandRepository.findById(brandId).get(), BrandDto.class);
    }

    private boolean isExistsBrandName(String brandName) {
        return !brandRepository.findByBrandName(brandName).isEmpty();
    }

    private boolean isExistsBrandId(Long brandId) {
        return !brandRepository.findById(brandId).isEmpty();
    }

}
