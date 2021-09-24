package com.wat.reservation_cinema_system_backend.ticket;

import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final ScreeningRepository screeningRepository;

    public List<TicketResponseDto> getAllTicketsForScreening(Long screeningId) {
        ScreeningEntity screeningEntity = screeningRepository.findById(screeningId).orElseThrow(
                () -> new RuntimeException("Screening not found"));
        List<TicketEntity> ticketEntities = screeningEntity.getTickets();
        List<TicketResponseDto> ticketsResponse = new ArrayList<>();

        ticketEntities.forEach(ticket -> {
            TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                    .screeningId(ticket.getScreening().getScreeningId())
                    .ticketId(ticket.getSeatReservedId())
                    .number(ticket.getSeat().getNumberSeat())
                    .row(ticket.getSeat().getRowSeat())
                    .build();

            ticketsResponse.add(ticketResponseDto);
        });
        return ticketsResponse;
    }
}
