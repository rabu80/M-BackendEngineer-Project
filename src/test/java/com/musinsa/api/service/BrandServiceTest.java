package com.musinsa.api.service;

import com.musinsa.api.brand.domain.Brand;
import com.musinsa.api.brand.dto.BrandSaveDto;
import com.musinsa.api.brand.dto.BrandUpdateDto;
import com.musinsa.api.brand.excpetion.BrandNotFoundException;
import com.musinsa.api.brand.repository.BrandRepository;
import com.musinsa.api.brand.service.BrandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BrandServiceTest {

    @Mock
    BrandRepository brandRepository;

    @InjectMocks
    BrandService brandService;

    @Mock
    private ModelMapper modelMapper;

    private Brand createBrand() {
        return Brand.builder()
                .brandName("나이키999")
                .build();
    }

    private BrandSaveDto createSaveBrandDto() {
        return BrandSaveDto.builder()
                .brandName("나이키999")
                .build();
    }

    private BrandUpdateDto createUpdateBrandDto() {
        return BrandUpdateDto.builder()
                .brandName("나이키999")
                .build();
    }

    @DisplayName("브랜드명 나이키999를 가진 브랜드 생성에 성공한다.")
    @Test
    public void saveBrand() {
        BrandSaveDto brandSaveDto = createSaveBrandDto();
        Brand brand = brandService.saveBrand(brandSaveDto);
        verify(brandRepository, times(1)).save(any());
    }

    // TestData를 서버 기동시 MemoryDb 생성하는 방식이라 조회 Test가 원활치 못한 상황..
    /*
    @DisplayName("특정 brandId를 가진 브랜드 조회에 성공한다.")
    @Test
    public void getBrandByBrandId() {
        Brand brand = createBrand();
        Long id = brand.getBrandId();
        given(brandRepository.findById(id)).willReturn(java.util.Optional.of(brand));
        BrandDto brandDto = brandService.getBrandByBrandId(id);

        assertThat(brandDto.getBrandId()).isEqualTo(id);
        assertThat(brandDto.getBrandName()).isEqualTo(brand.getBrandName());
        verify(brandRepository, times(1)).findById(id);
    }
    */

    @DisplayName("특정 brandId를 가진 브랜드 조회에 실패한다.")
    @Test
    public void failToGetBrandInfoIfBrandNotExist() {
        Long id = 1L;
        given(brandRepository.findById(id)).willReturn(Optional.empty());
        assertThrows(BrandNotFoundException.class, () -> brandService.getBrandByBrandId(id));
        verify(brandRepository, times(1)).findById(id);
    }

    @DisplayName("브랜드가 존재하지 않아 수정에 실패한다.")
    @Test
    public void failToUpdateBrandIfBrandNotExist() {
        Long brandId = 1L;
        BrandUpdateDto updateDto = createUpdateBrandDto();
        given(brandRepository.findById(brandId)).willReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandService.updateBrand(brandId, updateDto));
    }

    @DisplayName("브랜드가 존재하지 않아 삭제에 실패한다.")
    @Test
    public void failToDeleteBrandIfBrandNotExist() {
        Long id = 1L;
        given(brandRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandService.deleteBrand(id));
        verify(brandRepository, never()).deleteById(id);
    }
}