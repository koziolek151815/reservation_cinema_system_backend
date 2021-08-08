package com.wat.reservation_cinema_system_backend.image;

import com.wat.reservation_cinema_system_backend.entities.ImageEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageService {

    public final ImageRepository imageRepository;

    public ImageEntity uploadImage(MultipartFile imageFile) throws IOException {
        if(imageFile==null)return null;


        ImageEntity imageEntity = ImageEntity.builder()
                .originalFilename(imageFile.getOriginalFilename())
                .bytes(imageFile.getBytes())
                .build();

        return imageRepository.save(imageEntity);
    }
}
