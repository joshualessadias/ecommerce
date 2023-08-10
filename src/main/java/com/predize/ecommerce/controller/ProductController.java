package com.predize.ecommerce.controller;

import com.predize.ecommerce.service.ProductService;
import com.predize.ecommerce.service.dto.request.CreateProductRequestDTO;
import com.predize.ecommerce.service.dto.request.UpdateProductRequestDTO;
import com.predize.ecommerce.service.dto.response.ProductResponseDTO;
import com.predize.ecommerce.service.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService service;

    @GetMapping("/{productId}")
    public Response<ProductResponseDTO> findProduct(@PathVariable("productId") Long productId) {
        var response = new Response<ProductResponseDTO>();

        response.setData(service.findByIdToDTO(productId));
        response.setOk();

        return response;
    }

    @GetMapping()
    public Response<List<ProductResponseDTO>> findAllProducts() {
        var response = new Response<List<ProductResponseDTO>>();

        response.setData(service.findAllProducts());
        response.setOk();

        return response;
    }

    @PostMapping()
    public Response<ProductResponseDTO> createProduct(
            @RequestPart("pictures") MultipartFile pictures,
            @RequestBody CreateProductRequestDTO data
    ) throws IOException {
        var response = new Response<ProductResponseDTO>();

        response.setData(service.createProduct(data, pictures));
        response.setOk();

        return response;
    }

    @PutMapping("/{productId}")
    public Response<ProductResponseDTO> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody UpdateProductRequestDTO data
    ) {
        var response = new Response<ProductResponseDTO>();

        response.setData(service.updateProduct(productId, data));
        response.setOk();

        return response;
    }

    @DeleteMapping("/{productId}")
    public Response<Void> deleteProduct(@PathVariable("productId") Long productId) {
        var response = new Response<Void>();

        service.deleteProduct(productId);
        response.setOk();

        return response;
    }
}
