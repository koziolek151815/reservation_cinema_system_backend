package com.wat.reservation_cinema_system_backend.ticket;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

    private Long ticketId;
    private Integer row;
    private Integer number;
    private Long reservationId;
    private Long screeningId;
    private Long ticketTypeId;
    private Boolean made;
    private Boolean paid;
}
