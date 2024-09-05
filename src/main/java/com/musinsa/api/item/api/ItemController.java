package com.musinsa.api.item.api;

import com.musinsa.api.item.dto.ItemDto;
import com.musinsa.api.item.dto.ItemSaveDto;
import com.musinsa.api.item.dto.ItemUpdateDto;
import com.musinsa.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    // 의존성 주입을 위한 생성자
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("api/v1/items")
    @Operation(summary = "상품등록 API", description = "상품정보를 입력한다.")
    void saveItem(@RequestBody ItemSaveDto itemSaveDto) throws Exception {
        itemService.saveItem(itemSaveDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("api/v1/items/{itemId}")
    @Operation(summary = "상품수정 API", description = "상품정보를 수정한다.")
    public void updateItem(@PathVariable("itemId") Long itemId,
                              @Valid @RequestBody ItemUpdateDto itemUpdateDto) {
        itemService.updateItem(itemId, itemUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("api/v1/items/{itemId}")
    @Operation(summary = "상품삭제 API", description = "상품정보를 삭제한다.")
    public void deleteProduct(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/items")
    @Operation(summary = "전체 상품조회 API", description = "전체 상품정보를 조회한다.")
    List<ItemDto> itemList() {
        return itemService.getAllItems();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/items/{itemId}")
    @Operation(summary = "상품ID를 이용한 상품조회 API", description = "상품ID기준 상품 1건을 조회한다.(Unique)")
    ItemDto getItemByItemId(@PathVariable("itemId") Long itemId) {
        return itemService.getItemByItemId(itemId);
    }

}
