package com.wat.reservation_cinema_system_backend.ticket;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketListRequestDto {
    private List<TicketRequestDto> ticketsList;
    private double price;
}
