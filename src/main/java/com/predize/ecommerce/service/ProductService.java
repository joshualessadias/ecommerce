package com.predize.ecommerce.service;

import com.predize.ecommerce.enums.MessageEnum;
import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.model.Product;
import com.predize.ecommerce.repository.ProductRepository;
import com.predize.ecommerce.service.dto.request.CreateProductRequestDTO;
import com.predize.ecommerce.service.dto.request.UpdateProductRequestDTO;
import com.predize.ecommerce.service.dto.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final PictureService pictureService;

    public List<ProductResponseDTO> findAllProducts() {
        var productList = repository.findAll();

        var dtoList = new ArrayList<ProductResponseDTO>();

        productList.forEach(product -> {
            var dto = new ProductResponseDTO(product);
            dtoList.add(dto);
        });

        return dtoList;
    }

    public ProductResponseDTO createProduct(CreateProductRequestDTO request) {
        var product = buildProductFromRequestDto(request);

        request.getPictures().forEach(pictureName -> pictureService.createPicture(product, pictureName));

        return new ProductResponseDTO(repository.save(product));
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long productId, UpdateProductRequestDTO request) {
        var product = findById(productId);

        if (request.getName() != null && !request.getName().equals(product.getName()))
            product.setName(request.getName());
        if (request.getPrice() != null && !request.getPrice().equals(product.getPrice()))
            product.setPrice(request.getPrice());
        if (request.getStock() != null && !request.getStock().equals(product.getStock()))
            product.setStock(request.getStock());
        if (request.getPictures() != null) {
            if (request.getPictures().size() == 0)
                product.clearPictures();
            else {
                var pictureList = product.getPictureList().stream().map(Picture::getName).sorted().toList();
                Collections.sort(request.getPictures());

                if (!request.getPictures().equals(pictureList))
                    request.getPictures().forEach(pictureName -> pictureService.createPicture(product, pictureName));
            }
        }

        return new ProductResponseDTO(repository.save(product));
    }

    public void deleteProduct(Long productId) {
        var product = findById(productId);

        repository.delete(product);
    }

    public ProductResponseDTO findByIdToDTO(Long productId) {
        return new ProductResponseDTO(findById(productId));
    }

    public Product findById(Long productId) {
        return repository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageEnum.PRODUCT_NOT_FOUND.getDescription()));
    }

    private Product buildProductFromRequestDto(CreateProductRequestDTO request) {
        var product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return product;
    }
}
