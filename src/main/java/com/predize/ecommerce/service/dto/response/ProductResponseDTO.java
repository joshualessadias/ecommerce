package com.predize.ecommerce.service.dto.response;

import com.predize.ecommerce.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private List<byte[]> pictureList = new ArrayList<>();

    public ProductResponseDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        product.getPictureList().forEach(picture -> this.pictureList.add(picture.getImageData()));
    }
}
