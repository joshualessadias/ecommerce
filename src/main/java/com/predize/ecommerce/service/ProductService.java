package com.predize.ecommerce.service;

import com.predize.ecommerce.enums.MessageEnum;
import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.model.Product;
import com.predize.ecommerce.repository.ProductRepository;
import com.predize.ecommerce.service.dto.request.ProductRequestDTO;
import com.predize.ecommerce.service.dto.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final PictureService pictureService;

    public ProductResponseDTO findByIdToDTO(Long productId) {
        return new ProductResponseDTO(findById(productId));
    }

    public Product findById(Long productId) {
        return repository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageEnum.PRODUCT_NOT_FOUND.getDescription()));
    }

    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        var product = buildProductFromRequestDto(request);

        request.getPictures().forEach(pictureName -> pictureService.createPicture(product, pictureName));

        return new ProductResponseDTO(repository.save(product));
    }

    private Product buildProductFromRequestDto(ProductRequestDTO request) {
        var product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return product;
    }
}
