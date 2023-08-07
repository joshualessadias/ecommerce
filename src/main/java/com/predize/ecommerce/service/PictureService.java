package com.predize.ecommerce.service;

import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository repository;

    public List<Picture> findByProductId(Long productId) {
        return repository.findAllByProductId(productId);
    }
}
