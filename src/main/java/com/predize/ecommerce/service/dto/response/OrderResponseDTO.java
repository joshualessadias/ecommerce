package com.predize.ecommerce.service.dto.response;

import com.predize.ecommerce.model.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDTO {
    private BigDecimal totalPrice;
    private List<ProductOrderResponseDTO> productOrderList = new ArrayList<>();

    public OrderResponseDTO(Order order) {
        this.totalPrice = order.getPrice();
        order.getProductOrderList().forEach(productOrder ->
                this.productOrderList.add(new ProductOrderResponseDTO(productOrder)));
    }
}
