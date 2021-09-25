package com.wat.reservation_cinema_system_backend.screening.dto;

import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningWithAuditoriumResponseDto {
    private ScreeningResponseDto screening;
    private AuditoriumResponseDto auditorium;
}
