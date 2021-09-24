package com.wat.reservation_cinema_system_backend.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
}
