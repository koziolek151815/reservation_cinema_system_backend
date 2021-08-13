package com.wat.reservation_cinema_system_backend.screening.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningResponseDto {
    private Long screeningId;
    private Long movieId;
    private Long auditoriumId;
    private LocalDateTime startScreening;
    private LocalDateTime endScreening;
}

