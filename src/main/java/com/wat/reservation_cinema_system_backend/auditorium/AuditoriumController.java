package com.wat.reservation_cinema_system_backend.auditorium;

import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumRequestDto;
import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @PostMapping
    @PreAuthorize("hasRole('worker')")
    public AuditoriumResponseDto addAuditoriumWithSeats(@RequestBody AuditoriumRequestDto auditoriumRequestDto) {
        return auditoriumService.addAuditoriumWithSeats(auditoriumRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('worker')")
    public List<AuditoriumResponseDto> getAllAuditoriums(){
        return auditoriumService.getAllAuditoriums();
    }
}
