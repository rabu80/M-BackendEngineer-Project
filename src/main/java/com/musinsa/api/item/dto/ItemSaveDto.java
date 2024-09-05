package com.musinsa.api.item.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveDto {

  @NotNull
  private String brandName;

  @NotNull
  @Min(value = 100)
  private Long itemPrice;

  @NotNull
  private String categoryName;

  @Builder
  public ItemSaveDto(String brandName, Long itemPrice, String categoryName) {
    this.brandName = brandName;
    this.itemPrice = itemPrice;
    this.categoryName = categoryName;
  }
}
