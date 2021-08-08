package com.wat.reservation_cinema_system_backend.image;

import com.wat.reservation_cinema_system_backend.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
