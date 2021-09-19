package com.wat.reservation_cinema_system_backend.screening.dto;

import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScreeningsDayDto {
    private MovieResponseDto movie;
    private List<ScreeningResponseDto> screenings;
}
