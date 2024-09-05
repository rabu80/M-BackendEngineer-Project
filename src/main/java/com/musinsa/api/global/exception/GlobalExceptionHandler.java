package com.musinsa.api.global.exception;

import com.musinsa.api.brand.excpetion.BrandNotFoundException;
import com.musinsa.api.brand.excpetion.DuplicateBrandException;
import com.musinsa.api.item.exception.CategoryNotFoundException;
import com.musinsa.api.item.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.musinsa.api.global.constants.ResponseConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected final ResponseEntity<String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(exception.getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    protected final ResponseEntity<String> handleBrandNotFoundException(
            BrandNotFoundException exception) {
        return BRAND_NOT_FOUND;
    }

    @ExceptionHandler(DuplicateBrandException.class)
    protected final ResponseEntity<String> handleDuplicateBrandException(
            DuplicateBrandException exception) {
        return DUPLICATION_BRAND;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    protected final ResponseEntity<String> handleCategoryNotFoundException(
            CategoryNotFoundException exception) {
        return CATEGORY_NOT_FOUND;
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected final ResponseEntity<String> handlItemNotFoundException(
            ItemNotFoundException exception) {
        return ITEM_NOT_FOUND;
    }


}
