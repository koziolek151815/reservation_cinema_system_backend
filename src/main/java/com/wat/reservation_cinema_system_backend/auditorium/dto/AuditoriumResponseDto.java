package com.wat.reservation_cinema_system_backend.auditorium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriumResponseDto {
    private Long auditoriumId;
    private String name;
    private Integer rows;
    private Integer numbers;
}
