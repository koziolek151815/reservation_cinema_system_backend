package com.wat.reservation_cinema_system_backend.ticket;

import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
    List<TicketEntity> findAllByScreeningEqualsAndMadeTrue(ScreeningEntity screeningEntity);
}
