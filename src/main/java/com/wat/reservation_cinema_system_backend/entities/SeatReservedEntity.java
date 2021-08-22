package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserved_seats")
public class SeatReservedEntity {
    @Id
    @GeneratedValue
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
}