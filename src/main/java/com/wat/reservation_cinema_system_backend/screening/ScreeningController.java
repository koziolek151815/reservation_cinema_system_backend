package com.wat.reservation_cinema_system_backend.screening;

import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;

    @PostMapping
    @PreAuthorize("hasRole('worker')")
    public ResponseEntity<?> addScreening(@RequestBody ScreeningRequestDto screeningRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(screeningService.addScreening(screeningRequestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getMoviesScreeningsForDay(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(screeningService.getMoviesScreeningsForDay(date));
    }

    @GetMapping("/{screeningId}")
    public ResponseEntity<?> getScreeningWithAuditoriumById(@PathVariable Long screeningId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(screeningService.getScreeningWithAuditoriumById(screeningId));
    }
    @PreAuthorize("hasRole('worker')")
    @GetMapping("/screeningDayAndAuditorium")
    public ResponseEntity<?> getScreeningsByDayAndAuditorium(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("auditoriumId") Long auditoriumId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(screeningService.getScreeningsForDayAndAuditorium(date,auditoriumId));
    }
}
