package com.wat.reservation_cinema_system_backend.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

//    @GetMapping
//    public ReservationResponseDto getCurrentReservationOrCreate() {
//        return reservationService.getCurrentReservationOrCreate();
//    }
//
//    @PostMapping
//    public void makeReservation() {
//        reservationService.makeReservation();
//    }

    @GetMapping
    public List<ReservationInfoResponseDto> getUserReservationsHistory(){
        return reservationService.getAllReservationsByUser();
    }

    @DeleteMapping("/{reservationId}")
    public void cancelReservationByUser(@PathVariable Long reservationId){
        reservationService.cancelReservationByUser(reservationId);
    }
    @PreAuthorize("hasRole('worker')")
    @GetMapping("/{screeningId}")
    public List<ReservationInfoResponseDto> getReservationsForScreening(@PathVariable Long screeningId){
        return reservationService.getReservationsForScreening(screeningId);
    }
    @PreAuthorize("hasRole('worker')")
    @PutMapping("/{reservationId}")
    public void changeStatusOnPaid(@PathVariable Long reservationId){
        reservationService.changeStatusOnPaid(reservationId);
    }
}
