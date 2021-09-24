package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.entities.ReservationEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity,Long> {
    Optional<ReservationEntity> findByUserEqualsAndAndMadeFalse(UserEntity userEntity);
}
