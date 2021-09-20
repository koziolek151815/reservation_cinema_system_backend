package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity,Long> {
}
