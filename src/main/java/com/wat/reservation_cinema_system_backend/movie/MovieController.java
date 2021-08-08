package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.movie.dto.MovieRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.addMovie(movieRequestDto));
    }
}
