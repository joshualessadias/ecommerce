package com.predize.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageEnum {
    PRODUCT_NOT_FOUND("Product was not found");

    private String description;
}
