package com.wat.reservation_cinema_system_backend.auditorium;

import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumRequestDto;
import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public AuditoriumResponseDto addAuditoriumWithSeats(@RequestBody AuditoriumRequestDto auditoriumRequestDto) {
        return auditoriumService.addAuditoriumWithSeats(auditoriumRequestDto);
    }
}
