package com.wat.reservation_cinema_system_backend.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatReservedId;
    @ManyToOne
    @JoinColumn(name="seat_id")
    private SeatEntity seat;
    @ManyToOne
    @JoinColumn(name="reservation_id")
    private ReservationEntity reservation;
    @ManyToOne
    @JoinColumn(name="screening_id")
    private ScreeningEntity screening;
    @ManyToOne
    @JoinColumn(name="ticket_type_id")
    private TicketTypeEntity ticketTypeEntity;
    private Boolean made;
    private Boolean paid;
}
