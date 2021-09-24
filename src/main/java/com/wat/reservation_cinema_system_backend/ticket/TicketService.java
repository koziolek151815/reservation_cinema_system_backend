package com.wat.reservation_cinema_system_backend.ticket;

import com.wat.reservation_cinema_system_backend.entities.*;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.seat.SeatRepository;
import com.wat.reservation_cinema_system_backend.ticketType.TicketTypeRepository;
import com.wat.reservation_cinema_system_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final ScreeningRepository screeningRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final SeatRepository seatRepository;


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

    public void addTicketToReservation(Long screeningId, TicketRequestDto ticketRequestDto) {
        ScreeningEntity screeningEntity = screeningRepository.findById(screeningId).orElseThrow(
                () -> new RuntimeException("Screening not found"));
        TicketTypeEntity ticketTypeEntity = ticketTypeRepository.findById(ticketRequestDto.getTicketTypeId()).orElseThrow(
                () -> new RuntimeException("Ticket type not found"));
        SeatEntity seatEntity = seatRepository.findById(ticketRequestDto.getSeatId()).orElseThrow(
                () -> new RuntimeException("SeatNotFound"));

        if (checkIfTaken(seatEntity,screeningEntity)) {
            throw new RuntimeException("Seat is taken");
        }

        UserEntity currentUser = userService.getCurrentUser();
        ReservationEntity currentReservation = currentUser.getReservations().stream().filter(
                reservation -> !reservation.getMade()).findFirst().orElseThrow(() -> new RuntimeException("Reservation not found"));
        ticketRepository.save(TicketEntity.builder()
                .made(false)
                .paid(false)
                .screening(screeningEntity)
                .ticketTypeEntity(ticketTypeEntity)
                .reservation(currentReservation)
                .seat(seatEntity)
                .build());
    }

    private Boolean checkIfTaken(SeatEntity seatEntity, ScreeningEntity screeningEntity) {
        Optional<TicketEntity> ticketToReserve = ticketRepository.findBySeatEqualsAndScreeningEquals(seatEntity,screeningEntity);
        return ticketToReserve.isPresent();
    }
}
