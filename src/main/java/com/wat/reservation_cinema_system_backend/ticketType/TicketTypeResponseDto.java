package com.wat.reservation_cinema_system_backend.ticketType;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeResponseDto {
    private Long ticketTypeId;
    private Long price;
    private String name;
}
