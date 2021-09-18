package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue
    private Long seatId;
    private Integer rowSeat;
    private Integer numberSeat;
    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;
    @OneToMany(mappedBy="seat")
    private List<TicketEntity> tickets= new ArrayList<>();
}
