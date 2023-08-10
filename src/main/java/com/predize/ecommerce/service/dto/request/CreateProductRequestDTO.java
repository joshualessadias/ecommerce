package com.predize.ecommerce.service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateProductRequestDTO implements Serializable {
    private String name;
    private BigDecimal price;
    private Integer stock;
}
