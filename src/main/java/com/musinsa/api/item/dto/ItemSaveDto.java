package com.musinsa.api.item.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveDto {

  @NotEmpty
  private String brandName;

  @NotEmpty
  @Min(value = 100)
  @Max(value = 1000000000)
  private Long itemPrice;

  @NotEmpty
  private String categoryName;

  @Builder
  public ItemSaveDto(String brandName, Long itemPrice, String categoryName) {
    this.brandName = brandName;
    this.itemPrice = itemPrice;
    this.categoryName = categoryName;
  }
}
