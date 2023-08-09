package com.predize.ecommerce.controller;

import com.predize.ecommerce.service.ProductService;
import com.predize.ecommerce.service.dto.request.ProductRequestDTO;
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

        response.setData(service.findByIdToDTO(productId));
        response.setOk();

        return response;
    }

    @PostMapping()
    public Response<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO data) {
        var response = new Response<ProductResponseDTO>();

        response.setData(service.createProduct(data));
        response.setOk();

        return response;
    }

    @PutMapping("/{productId}")
    public Response<ProductResponseDTO> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductRequestDTO data
    ) {
        var response = new Response<ProductResponseDTO>();

        response.setData(service.updateProduct(productId, data));
        response.setOk();

        return response;
    }
}
