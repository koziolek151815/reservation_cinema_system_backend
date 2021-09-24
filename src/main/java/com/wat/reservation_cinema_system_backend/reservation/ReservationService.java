package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.entities.ReservationEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import com.wat.reservation_cinema_system_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ReservationResponseDto getCurrentReservationOrCreate() {
        UserEntity currentUser = userService.getCurrentUser();
        Optional<ReservationEntity> currentReservation = reservationRepository.findByUserEqualsAndMadeFalse(currentUser);
        if (!currentReservation.isPresent()) {
            ReservationEntity reservationEntity = ReservationEntity.builder()
                    .made(false)
                    .paid(false)
                    .user(currentUser)
                    .screening(null)
                    .tickets(new ArrayList<>())
                    .build();
            reservationRepository.save(reservationEntity);
            return ReservationResponseDto.builder().reservationId(reservationEntity.getId()).build();
        }
        return ReservationResponseDto.builder().reservationId(currentReservation.get().getId()).build();
    }
}
