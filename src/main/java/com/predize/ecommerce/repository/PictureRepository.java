package com.predize.ecommerce.repository;

import com.predize.ecommerce.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByProductId(Long productId);
}
