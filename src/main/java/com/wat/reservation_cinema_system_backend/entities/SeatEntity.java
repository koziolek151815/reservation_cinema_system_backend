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
@Entity(name = "seats")
public class SeatEntity {
    @Id
    private Long seatId;
    private Integer rowofSeat;
    private Integer numberofSeat;
    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;
    @OneToMany(mappedBy="seat")
    private List<SeatReservedEntity> seatsReserved= new ArrayList<>();
}
