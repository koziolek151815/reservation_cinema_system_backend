package com.wat.reservation_cinema_system_backend.ticketType;

import com.wat.reservation_cinema_system_backend.entities.TicketTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeEntity> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }
}
