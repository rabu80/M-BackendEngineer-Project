package com.musinsa.api.item.repository;

import com.musinsa.api.item.domain.Item;
import com.musinsa.api.item.dto.BrandItemInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategoryName(String categoryName);

    List<Item> findByBrandId(Long brandId);

    @Query(value = "select brand_id as brandId, sum(min_item_price) as itemPriceSum from"
            + " (select brand_id, category_name, min(item_price) as min_item_price from item "
            + "group by brand_id, category_name) group by brand_id ", nativeQuery = true)
    List<BrandItemInterface>findBrandItemPriceSum();
}
