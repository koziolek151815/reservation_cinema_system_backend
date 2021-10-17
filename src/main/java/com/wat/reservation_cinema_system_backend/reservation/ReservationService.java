package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.entities.ReservationEntity;
import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.ticket.TicketRepository;
import com.wat.reservation_cinema_system_backend.ticket.TicketResponseDto;
import com.wat.reservation_cinema_system_backend.ticket.TicketService;
import com.wat.reservation_cinema_system_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final TicketRepository ticketRepository;
    private final ScreeningRepository screeningRepository;

    //    public ReservationResponseDto getCurrentReservationOrCreate() {
//        UserEntity currentUser = userService.getCurrentUser();
//        Optional<ReservationEntity> currentReservation = reservationRepository.findByUserEqualsAndMadeFalse(currentUser);
//        if (!currentReservation.isPresent()) {
//            ReservationEntity reservationEntity = ReservationEntity.builder()
//                    .made(false)
//                    .paid(false)
//                    .user(currentUser)
//                    .screening(null)
//                    .tickets(new ArrayList<>())
//                    .build();
//            reservationRepository.save(reservationEntity);
//            return ReservationResponseDto.builder().reservationId(reservationEntity.getId()).build();
//        }
//        return ReservationResponseDto.builder().reservationId(currentReservation.get().getId()).build();
//    }
//
//    public void makeReservation() {
//        UserEntity currentUser = userService.getCurrentUser();
//        ReservationEntity reservationToProcess = currentUser.getReservations().stream().filter(
//                reservation -> !reservation.getMade()).findFirst().orElseThrow(() -> new RuntimeException("Reservation not found"));
//        reservationToProcess.setMade(true);
//        reservationRepository.save(reservationToProcess);
//
//        List<TicketEntity> ticketEntities = reservationToProcess.getTickets();
//        ticketEntities.forEach(ticketEntity -> {
//            ticketEntity.setMade(true);
//            ticketRepository.save(ticketEntity);
//        });
//    }
    public List<ReservationInfoResponseDto> getAllReservationsByUser() {
        UserEntity currentUser = userService.getCurrentUser();
        List<ReservationEntity> reservationEntities = reservationRepository.findReservationsByUser(currentUser);
        ArrayList<ReservationInfoResponseDto> reservationInfoResponseDtoArrayList = new ArrayList<>();
        reservationEntities.forEach(reservationEntity ->
                reservationInfoResponseDtoArrayList.add(ReservationInfoResponseDto.builder()
                        .reservationId(reservationEntity.getId())
                        .price(null)
                        .auditoriumName(reservationEntity.getScreening().getAuditorium().getName())
                        .screeningDate(reservationEntity.getScreening().getStartScreening())
                        .movie(reservationEntity.getScreening().getMovie().getTitle())
                        .paid(reservationEntity.getPaid())
                        .tickets(reservationEntity.getTickets().stream().map(ticketEntity -> TicketResponseDto.builder()
                                .number(ticketEntity.getSeat().getNumberSeat())
                                .ticketId(ticketEntity.getSeatReservedId())
                                .row(ticketEntity.getSeat().getRowSeat())
                                .ticketTypeName(ticketEntity.getTicketTypeEntity().getName())
                                .build()).collect(Collectors.toList()))
                        .build()));
        return reservationInfoResponseDtoArrayList;
    }

    public void cancelReservationByUser(Long reservationId) {
        ReservationEntity reservationToCancel = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationToCancel.getTickets().forEach(ticketRepository::delete);
        reservationRepository.delete(reservationToCancel);
    }

    public List<ReservationInfoResponseDto> getReservationsForScreening(Long screeningId) {
        ScreeningEntity screeningEntity = screeningRepository.findById(screeningId).orElseThrow(() -> new RuntimeException("Screening not found"));
        List<ReservationEntity> reservationEntities = reservationRepository.findAllByScreeningOrderByPaid(screeningEntity);
        ArrayList<ReservationInfoResponseDto> reservationInfoResponseDtoArrayList = new ArrayList<>();
        reservationEntities.forEach(reservationEntity ->
                reservationInfoResponseDtoArrayList.add(ReservationInfoResponseDto.builder()
                        .reservationId(reservationEntity.getId())
                        .price(null)
                        .userEmail(reservationEntity.getUser() != null ? reservationEntity.getUser().getEmail() : "Anonim")
                        .auditoriumName(reservationEntity.getScreening().getAuditorium().getName())
                        .screeningDate(reservationEntity.getScreening().getStartScreening())
                        .movie(reservationEntity.getScreening().getMovie().getTitle())
                        .paid(reservationEntity.getPaid())
                        .tickets(reservationEntity.getTickets().stream().map(ticketEntity -> TicketResponseDto.builder()
                                .number(ticketEntity.getSeat().getNumberSeat())
                                .ticketId(ticketEntity.getSeatReservedId())
                                .row(ticketEntity.getSeat().getRowSeat())
                                .ticketTypeName(ticketEntity.getTicketTypeEntity().getName())
                                .build()).collect(Collectors.toList()))
                        .build()));
        return reservationInfoResponseDtoArrayList;
    }

    public void changeStatusOnPaid(Long reservationId) {
        ReservationEntity reservationToUpdate = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Screening not found"));
        reservationToUpdate.setPaid(true);
        reservationRepository.save(reservationToUpdate);
    }

}
