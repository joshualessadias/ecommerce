package com.predize.ecommerce.service.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateProductRequestDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
}
