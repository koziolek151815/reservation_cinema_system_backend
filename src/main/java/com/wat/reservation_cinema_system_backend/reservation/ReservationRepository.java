package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.entities.ReservationEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity,Long> {
    Optional<ReservationEntity> findByUserEqualsAndMadeFalse(UserEntity userEntity);
    @Query("SELECT r FROM reservations r where r.user =?1 order by r.screening.startScreening desc")
    List<ReservationEntity> findReservationsByUser(UserEntity userEntity);
}
