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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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

    public ProductResponseDTO createProduct(CreateProductRequestDTO request, MultipartFile pictures) throws IOException {
        var product = buildProductFromRequestDto(request);

        pictureService.createPicture(product, pictures);
//        pictures.forEach(picture -> {
//            try {
//                pictureService.createPicture(product, picture);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

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
                var pictureRequestList = request.getPictures().stream().map(MultipartFile::getName).sorted().toList();

                if (!pictureRequestList.equals(pictureList))
                    request.getPictures().forEach(pictureName -> {
                        try {
                            pictureService.createPicture(product, pictureName);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            }
        }

        return new ProductResponseDTO(repository.save(product));
    }

    public void deleteProduct(Long productId) {
        var product = findById(productId);

        repository.delete(product);
    }

    @Transactional
    public void updateStock(Product product, Integer purchaseQuantity) {
        var currentStock = product.getStock();

        product.setStock(currentStock - purchaseQuantity);

        repository.save(product);
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
