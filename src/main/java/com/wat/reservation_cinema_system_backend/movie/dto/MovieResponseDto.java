package com.wat.reservation_cinema_system_backend.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {
    Long movieId;
    String title;
    String director;
    String description;
    String moviePhotoName;
    Integer duration;
}
