package com.predize.ecommerce.service.dto.request;

import lombok.Getter;

@Getter
public class ProductOrderRequestDTO {
    private Long productId;
    private Integer quantity;
}
