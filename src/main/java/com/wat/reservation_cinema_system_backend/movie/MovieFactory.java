package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;

public class MovieFactory {
    public MovieResponseDto entityToMovieResponseDto(MovieEntity movieEntity){
        return MovieResponseDto.builder()
                .movieId(movieEntity.getMovieId())
                .title(movieEntity.getTitle())
                .director(movieEntity.getDirector())
                .description(movieEntity.getDescription())
                .build();
    }
    public MovieEntity movieRequestDtoToEntity(MovieRequestDto movieRequestDto){
        return MovieEntity.builder()
                .description(movieRequestDto.getDescription())
                .director(movieRequestDto.getDirector())
                .title(movieRequestDto.getTitle())
                .build();
    }
}
