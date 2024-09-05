package com.musinsa.api.item.dto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemUpdateDto {

  @Hidden
  private Long brandId;

  @NotEmpty
  private String brandName;

  @Min(value = 100)
  private Long itemPrice;

  @NotEmpty
  private String categoryName;

  @Builder
  public ItemUpdateDto(String brandName, Long itemPrice, String categoryName) {
    this.brandName = brandName;
    this.itemPrice = itemPrice;
    this.categoryName = categoryName;
  }
}
