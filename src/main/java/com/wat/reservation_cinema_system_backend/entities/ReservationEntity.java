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
@Entity(name = "reservations")
public class ReservationEntity {
    @Id
    private Long Id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    @OneToMany(mappedBy="reservation")
    private List<SeatReservedEntity> seatsReserved= new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="screening_id")
    private ScreeningEntity screening;


}
