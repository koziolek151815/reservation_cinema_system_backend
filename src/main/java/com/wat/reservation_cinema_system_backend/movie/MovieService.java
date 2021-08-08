package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.entities.ImageEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.exceptions.MovieNotFoundException;
import com.wat.reservation_cinema_system_backend.image.ImageService;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieFactory movieFactory;
    private final MovieRepository movieRepository;
    private final ImageService imageService;

    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) throws IOException {
        ImageEntity image = imageService.uploadImage(movieRequestDto.getPostPhoto());
        MovieEntity movieEntity = movieRepository.save(movieFactory.movieRequestDtoToEntity(movieRequestDto, image));
        return movieFactory.entityToMovieResponseDto(movieEntity);
    }
    public byte[] getPhotobytesByMovieId(Long movieId){
        MovieEntity relatedMovie = movieRepository
                .findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));

        ImageEntity relatedImage = relatedMovie.getMoviePhoto();

        if(relatedImage==null)return null;

        return Base64.getEncoder().encode(relatedImage.getBytes());
    }
}
