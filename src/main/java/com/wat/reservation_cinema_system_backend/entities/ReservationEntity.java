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
@Entity(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue
    private Long Id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    private Boolean made;
    private Boolean paid;
    private Double price;
    @OneToMany(mappedBy="reservation")
    private List<TicketEntity> tickets= new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="screening_id")
    private ScreeningEntity screening;


}
