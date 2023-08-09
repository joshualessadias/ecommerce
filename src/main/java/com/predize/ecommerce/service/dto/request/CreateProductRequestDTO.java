package com.predize.ecommerce.service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateProductRequestDTO {
    @NotBlank(message = "Product name is necessary")
    @NotNull(message = "Product name is necessary")
    private String name;
    @NotNull(message = "Product price is necessary")
    @Min(value = 0, message = "Product price cannot be negative")
    private BigDecimal price;
    @NotNull(message = "Product quantity in stock is necessary")
    @Min(value = 0, message = "Product quantity in stock cannot be negative")
    private Integer stock;
    private List<String> pictures;
}
