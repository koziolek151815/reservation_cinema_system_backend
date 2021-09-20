package com.wat.reservation_cinema_system_backend.ticket;

import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
