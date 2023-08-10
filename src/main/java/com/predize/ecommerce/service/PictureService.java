package com.predize.ecommerce.service;

import com.predize.ecommerce.model.Picture;
import com.predize.ecommerce.model.Product;
import com.predize.ecommerce.repository.PictureRepository;
import com.predize.ecommerce.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository repository;

    public void createPicture(Product product, MultipartFile file) throws IOException {
        var picture = new Picture();

        picture.setName(file.getName());
        picture.setImageData(ImageUtil.compressImage(file.getBytes()));

        product.clearPictures();
        product.addPicture(picture);
    }
}
