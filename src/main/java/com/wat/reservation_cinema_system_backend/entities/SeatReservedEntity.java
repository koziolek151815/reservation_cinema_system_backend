package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserved_seats")
public class SeatReservedEntity {
    @Id
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
