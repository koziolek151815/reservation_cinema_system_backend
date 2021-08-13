package com.wat.reservation_cinema_system_backend.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "screenings")
public class ScreeningEntity {
    @Id
    @GeneratedValue
    private Long screeningId;
    @ManyToOne
    @JoinColumn(name="movie_id")
    private MovieEntity movie;
    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;
    private LocalDateTime startScreening;
    private LocalDateTime endScreening;
    @OneToMany(mappedBy="screening")
    private List<SeatReservedEntity> seatsReserved=new ArrayList<>();
    @OneToMany(mappedBy="screening")
    private List<ReservationEntity> reservations=new ArrayList<>();
}
