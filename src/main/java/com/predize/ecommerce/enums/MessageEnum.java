package com.predize.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@AllArgsConstructor
public enum MessageEnum {
    PRODUCT_NOT_FOUND("Product was not found"),
    PRODUCT_ORDER_QUANTITY_INVALID("{0} quantity is not available for purchase");

    private final String description;

    public String getDescription() {
        return this.description;
    }

    public String getDescription(String args) {
        return MessageFormat.format(this.description, args);
    }
}
