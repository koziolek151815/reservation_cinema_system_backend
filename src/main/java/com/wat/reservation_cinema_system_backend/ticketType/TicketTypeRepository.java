package com.wat.reservation_cinema_system_backend.ticketType;

import com.wat.reservation_cinema_system_backend.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity,Long> {
}
