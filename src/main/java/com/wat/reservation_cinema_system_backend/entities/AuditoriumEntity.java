package com.wat.reservation_cinema_system_backend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "auditoriums")
public class AuditoriumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditoriumId;
    private String name;
    private Integer numberOfSeats;
    private Integer rows;
    private Integer numbers;
    @OneToMany(mappedBy="auditorium")
    private List<ScreeningEntity> screenings= new ArrayList<>();
    @OneToMany(mappedBy="auditorium")
    private List<SeatEntity> seats=new ArrayList<>();
}
