package com.wat.reservation_cinema_system_backend.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    String title;
    String director;
    String description;
    Integer duration;
    MultipartFile postPhoto;
}
