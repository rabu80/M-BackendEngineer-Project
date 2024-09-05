package com.musinsa.api.item.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(indexes = {@Index(name = "idx_item_01", columnList = "brand_id"), @Index(name = "idx_item_02", columnList = "category_name")})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itemId;

    @Column(length = 10, nullable = false)
    private Long brandId;

    @Column(length = 10, nullable = false)
    private Long itemPrice;

    @Column(length = 100, nullable = false)
    private String categoryName;

    @Builder
    public Item(Long brandId, Long itemPrice, String categoryName) {
        this.brandId = brandId;
        this.itemPrice = itemPrice;
        this.categoryName = categoryName;
    }
}
