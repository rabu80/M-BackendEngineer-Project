package com.musinsa.api.item.service;

import com.musinsa.api.brand.domain.Brand;
import com.musinsa.api.brand.excpetion.BrandNotFoundException;
import com.musinsa.api.brand.repository.BrandRepository;
import com.musinsa.api.item.domain.Item;
import com.musinsa.api.item.dto.ItemDto;
import com.musinsa.api.item.dto.ItemSaveDto;
import com.musinsa.api.item.dto.ItemUpdateDto;
import com.musinsa.api.item.exception.CategoryNotFoundException;
import com.musinsa.api.item.exception.ItemNotFoundException;
import com.musinsa.api.item.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.musinsa.api.global.util.GlobalUtils.isExistsCategoryName;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper, ModelMapper modelMapper1, BrandRepository brandRepository) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper1;
        this.brandRepository = brandRepository;
    }

    @Transactional
    public void saveItem(ItemSaveDto itemSaveDto) throws Exception {

        Long brandId = getBrandIdByName(itemSaveDto.getBrandName());
        if(!itemSaveDto.getBrandName().isEmpty() && brandId == null) {
            throw new BrandNotFoundException();
        }

        if(!itemSaveDto.getCategoryName().isEmpty() && !isExistsCategoryName(itemSaveDto.getCategoryName())) {
            throw new CategoryNotFoundException();
        }

        Item item = Item.builder()
                .brandId(brandId)
                .itemPrice(itemSaveDto.getItemPrice())
                .categoryName(itemSaveDto.getCategoryName())
                .build();

        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateDto itemUpdateDto) {

        if(!isExistsItem(itemId)) {
            throw new ItemNotFoundException();
        }

        Long brandId = getBrandIdByName(itemUpdateDto.getBrandName());
        if(!itemUpdateDto.getBrandName().isEmpty() && brandId == null) {
            throw new BrandNotFoundException();
        }

        if(!itemUpdateDto.getCategoryName().isEmpty() && !isExistsCategoryName(itemUpdateDto.getCategoryName())) {
            throw new CategoryNotFoundException();
        }

        Item item = Item.builder()
                .brandId(brandId)
                .itemPrice(itemUpdateDto.getItemPrice())
                .categoryName(itemUpdateDto.getCategoryName())
                .build();

        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long itemId) throws Exception {
        if(!isExistsItem(itemId)) {
            throw new ItemNotFoundException();
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional(readOnly = true)
    public ItemDto getItemByItemId(long itemId) {
        if(!isExistsItem(itemId)) {
            throw new ItemNotFoundException();
        }
        return modelMapper.map(itemRepository.findById(itemId), ItemDto.class);
    }

    @Transactional(readOnly = true)
    public List<ItemDto> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream()
                .map(entity -> modelMapper.map(entity, ItemDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private Long getBrandIdByName(String brandName) {
        List<Brand> list = brandRepository.findByBrandName(brandName);
        return !list.isEmpty()? list.get(0).getBrandId() : null;
    }

    @Transactional(readOnly = true)
    private boolean isExistsItem(Long itemId) {
        return !itemRepository.findById(itemId).isEmpty();
    }
}
