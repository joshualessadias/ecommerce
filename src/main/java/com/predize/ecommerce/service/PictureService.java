package com.predize.ecommerce.service;

import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.model.Product;
import com.predize.ecommerce.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository repository;

    public void createPicture(Product product, String pictureName) {
        var picture = new Picture();

        picture.setName(pictureName);

        product.clearPictures();
        product.addPicture(picture);
    }
}
