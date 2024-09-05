package com.musinsa.api.item.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {

  private Long itemId;
  private Long brandId;
  private Long itemPrice;
  private String categoryName;

  @Builder
  public ItemDto(Long itemId, Long brandId, Long itemPrice, String categoryName) {
    this.itemId = itemId;
    this.brandId = brandId;
    this.itemPrice = itemPrice;
    this.categoryName = categoryName;
  }

}
