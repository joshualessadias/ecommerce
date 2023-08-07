package com.predize.ecommerce.controller;

import com.predize.ecommerce.service.ProductService;
import com.predize.ecommerce.service.dto.response.ProductResponseDTO;
import com.predize.ecommerce.service.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/{productId}")
    public Response<ProductResponseDTO> findProduct(@PathVariable("productId") Long productId) {
        var response = new Response<ProductResponseDTO>();

        response.setOk();
        response.setData(service.findByIdToDTO(productId));

        return response;
    }
}
