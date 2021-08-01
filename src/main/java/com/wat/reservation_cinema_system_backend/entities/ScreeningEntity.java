package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "screenings")
public class ScreeningEntity {
    @Id
    private Long screeningId;
    @ManyToOne
    @JoinColumn(name="movie_id")
    private MovieEntity movie;
    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;
    @OneToMany(mappedBy="screening")
    private List<SeatReservedEntity> seatsReserved=new ArrayList<>();
    @OneToMany(mappedBy="screening")
    private List<ReservationEntity> reservations=new ArrayList<>();
}
