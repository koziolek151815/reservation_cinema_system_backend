package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addMovie(@ModelAttribute MovieRequestDto movieRequestDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.addMovie(movieRequestDto));
    }

    @RequestMapping(value = "/getPhoto", method = RequestMethod.GET)
    ResponseEntity<byte[]> getPhoto(@RequestParam Long movieId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieService.getPhotobytesByMovieId(movieId));
    }
    @GetMapping
    public List<MovieResponseDto> getAllMovies(){
        return movieService.getAllMovies();
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieRepository.deleteById(id);
    }
}
