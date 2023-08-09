package com.predize.ecommerce.service.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductOrderRequestDTO {
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
}
