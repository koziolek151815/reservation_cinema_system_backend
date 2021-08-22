package com.wat.reservation_cinema_system_backend.screening;

import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.movie.MovieFactory;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningResponseDto;
import lombok.*;

@RequiredArgsConstructor
public class ScreeningFactory {
    private final MovieFactory movieFactory;
    public ScreeningEntity screeningRequestDtoToEntity(ScreeningRequestDto screeningRequestDto, MovieEntity movieEntity, AuditoriumEntity auditoriumEntity) {
        return ScreeningEntity.builder()
                .auditorium(auditoriumEntity)
                .movie(movieEntity)
                .startScreening(screeningRequestDto.getStartScreening())
                .endScreening(screeningRequestDto.getEndScreening())
                .build();
    }

    public ScreeningResponseDto entityToScreeningResponseDto(ScreeningEntity screeningEntity){
        return ScreeningResponseDto.builder()
                .auditoriumId(screeningEntity.getAuditorium().getAuditoriumId())
                .movieResponseDto(movieFactory.entityToMovieResponseDto(screeningEntity.getMovie()))
                .screeningId(screeningEntity.getScreeningId())
                .startScreening(screeningEntity.getStartScreening())
                .endScreening(screeningEntity.getEndScreening())
                .build();
    }
}
