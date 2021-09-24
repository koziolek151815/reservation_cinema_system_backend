package com.wat.reservation_cinema_system_backend.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public int getAllReservationsForUser() {
        return 3;
    }

    @GetMapping
    public ReservationResponseDto getCurrentReservationOrCreate() {
        return reservationService.getCurrentReservationOrCreate();
    }
}
