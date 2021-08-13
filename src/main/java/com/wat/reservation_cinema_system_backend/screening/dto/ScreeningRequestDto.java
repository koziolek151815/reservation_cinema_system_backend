package com.wat.reservation_cinema_system_backend.screening.dto;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningRequestDto {
    private Long movieId;
    private Long auditoriumId;
    private LocalDateTime startScreening;
    private LocalDateTime endScreening;
}
