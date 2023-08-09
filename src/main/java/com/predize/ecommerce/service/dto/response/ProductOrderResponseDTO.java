package com.predize.ecommerce.service.dto.response;

import com.predize.ecommerce.model.ProductOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOrderResponseDTO {
    private String productName;
    private BigDecimal price;
    private Integer quantity;

    public ProductOrderResponseDTO(ProductOrder productOrder) {
        this.productName = productOrder.getProduct().getName();
        this.price = productOrder.getPrice();
        this.quantity = productOrder.getQuantity();
    }
}
