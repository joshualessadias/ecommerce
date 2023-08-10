package com.predize.ecommerce.service.dto.response;

import com.predize.ecommerce.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;

    public ProductResponseDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
