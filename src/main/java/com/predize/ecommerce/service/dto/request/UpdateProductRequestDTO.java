package com.predize.ecommerce.service.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class UpdateProductRequestDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private List<MultipartFile> pictures;
}
