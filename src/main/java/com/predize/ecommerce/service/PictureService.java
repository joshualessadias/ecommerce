package com.predize.ecommerce.service;

import com.predize.ecommerce.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository repository;
}
