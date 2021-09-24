package com.wat.reservation_cinema_system_backend.ticket;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private Long seatId;
    private Integer seatRow;
    private Integer seatNumber;
    private Long ticketTypeId;
}
