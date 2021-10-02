package com.wat.reservation_cinema_system_backend.ticketType;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeRequestDto {
    private double price;
    private String name;
}
