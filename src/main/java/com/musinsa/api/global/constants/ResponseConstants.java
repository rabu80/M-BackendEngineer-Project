package com.musinsa.api.global.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {
    public static final ResponseEntity<Void> OK =
            ResponseEntity.ok().build();

    public static final ResponseEntity<Void> CREATED =
            ResponseEntity.status(HttpStatus.CREATED).build();

    public static final ResponseEntity<Void> BAD_REQUEST =
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    public static final ResponseEntity<String> DUPLICATION_BRAND =
            new ResponseEntity<>("중복된 브랜드입니다.", HttpStatus.CONFLICT);

    public static final ResponseEntity<String> BRAND_NOT_FOUND =
            new ResponseEntity<>("존재하지 않는 브랜드입니다.", HttpStatus.BAD_REQUEST);

    public static final ResponseEntity<String> CATEGORY_NOT_FOUND =
            new ResponseEntity<>("존재하지 않는 카테고리입니다.", HttpStatus.BAD_REQUEST);

    public static final ResponseEntity<String> ITEM_NOT_FOUND =
            new ResponseEntity<>("존재하지 않는 상품입니다.", HttpStatus.BAD_REQUEST);
}
