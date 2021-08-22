package com.wat.reservation_cinema_system_backend.screening;

import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> addScreening(@RequestBody ScreeningRequestDto screeningRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(screeningService.addScreening(screeningRequestDto));
    }
    @GetMapping
    public ResponseEntity<?> getScreenings(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(screeningService.getScreenings());
    }
}
