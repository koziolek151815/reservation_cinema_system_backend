package com.wat.reservation_cinema_system_backend.screening;

import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumRepository;
import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.movie.MovieRepository;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final ScreeningFactory screeningFactory;

    public boolean isOverlapping(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public ScreeningResponseDto addScreening(ScreeningRequestDto screeningRequestDto) {
        // only for testing
        screeningRequestDto.setStartScreening(LocalDateTime.now().plusHours(3));
        screeningRequestDto.setEndScreening(LocalDateTime.now().plusHours(5));

        MovieEntity movieEntity = movieRepository.findById(screeningRequestDto.getMovieId()).
                orElseThrow(() -> new RuntimeException("Movie not found"));
        AuditoriumEntity auditoriumEntity = auditoriumRepository.findById(screeningRequestDto.getAuditoriumId()).
                orElseThrow(() -> new RuntimeException("Auditorium not found"));
        List<ScreeningEntity> screenings = auditoriumEntity.getScreenings();
        for (ScreeningEntity screeningEntity : screenings) {
            if (isOverlapping(screeningRequestDto.getStartScreening(), screeningRequestDto.getEndScreening(),
                    screeningEntity.getStartScreening(), screeningEntity.getEndScreening())) {
                throw new RuntimeException("Auditorium is taken");
            }
        }
        ScreeningEntity savedScreening = screeningRepository.save(screeningFactory
                .screeningRequestDtoToEntity(screeningRequestDto, movieEntity, auditoriumEntity));
        return screeningFactory.entityToScreeningResponseDto(savedScreening);
    }
}
