package com.wat.reservation_cinema_system_backend.auditorium;

import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumRequestDto;
import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;

public class AuditoriumFactory {
    public AuditoriumEntity auditoriumRequestDtoToEntity(AuditoriumRequestDto auditoriumRequestDto){
        return AuditoriumEntity.builder()
                .name(auditoriumRequestDto.getName())
                .rows(auditoriumRequestDto.getRows())
                .numbers(auditoriumRequestDto.getNumbers())
                .build();
    }

    public AuditoriumResponseDto entityToAuditoriumResponseDto(AuditoriumEntity auditoriumEntity){
        return AuditoriumResponseDto.builder()
                .auditoriumId(auditoriumEntity.getAuditoriumId())
                .name(auditoriumEntity.getName())
                .numbers(auditoriumEntity.getNumbers())
                .rows(auditoriumEntity.getRows())
                .build();
    }
}
