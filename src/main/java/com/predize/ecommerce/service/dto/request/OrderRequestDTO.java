package com.predize.ecommerce.service.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDTO {
    private List<ProductOrderRequestDTO> productOrderRequestList;
}
