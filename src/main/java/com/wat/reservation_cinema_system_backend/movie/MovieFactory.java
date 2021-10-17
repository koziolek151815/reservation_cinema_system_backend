package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.entities.ImageEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;

public class MovieFactory {
    public MovieResponseDto entityToMovieResponseDto(MovieEntity movieEntity){
        return MovieResponseDto.builder()
                .movieId(movieEntity.getMovieId())
                .title(movieEntity.getTitle())
                .duration(movieEntity.getDuration())
                .director(movieEntity.getDirector())
                .description(movieEntity.getDescription())
                .moviePhotoName(movieEntity.getMoviePhoto()==null?null:movieEntity.getMoviePhoto().getOriginalFilename())
                .build();
    }
    public MovieEntity movieRequestDtoToEntity(MovieRequestDto movieRequestDto, ImageEntity image){
        return MovieEntity.builder()
                .description(movieRequestDto.getDescription())
                .director(movieRequestDto.getDirector())
                .title(movieRequestDto.getTitle())
                .moviePhoto(image)
                .build();
    }
}
