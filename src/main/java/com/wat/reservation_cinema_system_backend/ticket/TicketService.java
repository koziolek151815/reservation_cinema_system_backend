package com.wat.reservation_cinema_system_backend.ticket;

import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumFactory;
import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumRepository;
import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import com.wat.reservation_cinema_system_backend.entities.*;
import com.wat.reservation_cinema_system_backend.mail.MailService;
import com.wat.reservation_cinema_system_backend.reservation.ReservationRepository;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.seat.SeatRepository;
import com.wat.reservation_cinema_system_backend.ticketType.TicketTypeRepository;
import com.wat.reservation_cinema_system_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
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
    private final ReservationRepository reservationRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final MailService mailService;


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

    public void addTicketsToReservation(Long screeningId, TicketListRequestDto ticketListRequestDto) throws MessagingException {
        UserEntity currentUser = userService.getCurrentUser();
        ScreeningEntity screening = screeningRepository.findById(screeningId).orElseThrow(
                () -> new RuntimeException("Screening not found"));
        ReservationEntity currentReservation = ReservationEntity.builder()
                .paid(false)
                .price(ticketListRequestDto.getPrice())
                .user(currentUser)
                .screening(screening)
                .tickets(new ArrayList<>())
                .build();
        reservationRepository.save(currentReservation);

        ticketListRequestDto.getTicketsList().forEach(ticketRequestDto -> {
            ScreeningEntity screeningEntity = screeningRepository.findById(screeningId).orElseThrow(
                    () -> new RuntimeException("Screening not found"));
            TicketTypeEntity ticketTypeEntity = ticketTypeRepository.findById(ticketRequestDto.getTicketTypeId()).orElseThrow(
                    () -> new RuntimeException("Ticket type not found"));
            AuditoriumEntity auditoriumEntity = auditoriumRepository.findById(ticketRequestDto.getAuditoriumId()).orElseThrow(
                    () -> new RuntimeException("Auditorium not found"));
            SeatEntity seatEntity = auditoriumEntity.getSeats().stream()
                    .filter(s -> s.getNumberSeat().equals(ticketRequestDto.getSeatNumber()) && s.getRowSeat().equals(ticketRequestDto.getSeatRow()))
                    .findFirst().orElseThrow(() -> new RuntimeException("Seat not found"));

            if (checkIfTaken(seatEntity, screeningEntity)) {
                throw new RuntimeException("Seat is taken");
            }

            ticketRepository.save(TicketEntity.builder()
                    .screening(screeningEntity)
                    .ticketTypeEntity(ticketTypeEntity)
                    .reservation(currentReservation)
                    .seat(seatEntity)
                    .build());
        });
        mailService.sendMail(currentUser.getEmail(), "Rezerwacja","<b>Dokonałeś rezerwacji na film "+ screening.getMovie().getTitle()+" " + formatDate(screening.getStartScreening())+ " Sala " + screening.getAuditorium().getAuditoriumId() +"</b><br>", true);
    }

    private Boolean checkIfTaken(SeatEntity seatEntity, ScreeningEntity screeningEntity) {
        Optional<TicketEntity> ticketToReserve = ticketRepository.findBySeatEqualsAndScreeningEquals(seatEntity, screeningEntity);
        return ticketToReserve.isPresent();
    }

    public void addTicketsToReservationWorker(Long screeningId, TicketListRequestDto ticketListRequestDto) {
        ScreeningEntity screening = screeningRepository.findById(screeningId).orElseThrow(
                () -> new RuntimeException("Screening not found"));
        ReservationEntity currentReservation = ReservationEntity.builder()
                .made(true)
                .paid(true)
                .price(ticketListRequestDto.getPrice())
                .screening(screening)
                .tickets(new ArrayList<>())
                .build();
        reservationRepository.save(currentReservation);
        ticketListRequestDto.getTicketsList().forEach(ticketRequestDto -> {
            ScreeningEntity screeningEntity = screeningRepository.findById(screeningId).orElseThrow(
                    () -> new RuntimeException("Screening not found"));
            TicketTypeEntity ticketTypeEntity = ticketTypeRepository.findById(ticketRequestDto.getTicketTypeId()).orElseThrow(
                    () -> new RuntimeException("Ticket type not found"));
            AuditoriumEntity auditoriumEntity = auditoriumRepository.findById(ticketRequestDto.getAuditoriumId()).orElseThrow(
                    () -> new RuntimeException("Auditorium not found"));
            SeatEntity seatEntity = auditoriumEntity.getSeats().stream()
                    .filter(s -> s.getNumberSeat().equals(ticketRequestDto.getSeatNumber()) && s.getRowSeat().equals(ticketRequestDto.getSeatRow()))
                    .findFirst().orElseThrow(() -> new RuntimeException("Seat not found"));

            if (checkIfTaken(seatEntity, screeningEntity)) {
                throw new RuntimeException("Seat is taken");
            }

            ticketRepository.save(TicketEntity.builder()
                    .made(true)
                    .paid(true)
                    .screening(screeningEntity)
                    .ticketTypeEntity(ticketTypeEntity)
                    .reservation(currentReservation)
                    .seat(seatEntity)
                    .build());
        });
    }

    private String formatDate(LocalDateTime localDateTime){
        String year = String.valueOf(localDateTime.getYear());
        String month = String.valueOf(localDateTime.getMonthValue());
        String day = String.valueOf(localDateTime.getDayOfMonth());
        String hours = String.valueOf(localDateTime.getHour());
        String minutes = String.valueOf(localDateTime.getMinute());
        return day +"."+ month +"."+ year + " "+hours+":0"+ minutes;
    }

}
