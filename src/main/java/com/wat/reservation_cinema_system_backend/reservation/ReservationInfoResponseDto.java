package com.wat.reservation_cinema_system_backend.reservation;

import com.wat.reservation_cinema_system_backend.ticket.TicketResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoResponseDto {
    private Long reservationId;
    private String userEmail;
    private String movie;
    private String auditoriumName;
    private LocalDateTime screeningDate;
    private Boolean paid;
    private Double price;
    private List<TicketResponseDto> tickets;
}
