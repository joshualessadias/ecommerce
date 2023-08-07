package com.predize.ecommerce.service.dto.response;

import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponseDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private List<String> pictureList;

    public ProductResponseDTO(Product product, List<Picture> pictureList) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        pictureList.forEach(picture -> this.pictureList.add(picture.getName()));
    }
}
