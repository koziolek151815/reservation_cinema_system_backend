package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieFactory movieFactory;
    private final MovieRepository movieRepository;
    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto){
        MovieEntity movieEntity = movieRepository.save(movieFactory.movieRequestDtoToEntity(movieRequestDto));
        return movieFactory.entityToMovieResponseDto(movieEntity);
    }
}
