package com.wat.reservation_cinema_system_backend.screening;

import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumFactory;
import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumRepository;
import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.movie.MovieFactory;
import com.wat.reservation_cinema_system_backend.movie.MovieRepository;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import com.wat.reservation_cinema_system_backend.screening.dto.MovieScreeningsDayDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningResponseDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningWithAuditoriumResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {
    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final ScreeningFactory screeningFactory;
    private final MovieFactory movieFactory;
    private final AuditoriumFactory auditoriumFactory;

    public boolean isOverlapping(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public ScreeningResponseDto addScreening(ScreeningRequestDto screeningRequestDto) {
        screeningRequestDto.setStartScreening(screeningRequestDto.getStartScreening().plusHours(1));
        screeningRequestDto.setEndScreening(screeningRequestDto.getEndScreening().plusHours(1));

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

    public List<MovieScreeningsDayDto> getMoviesScreeningsForDay(LocalDate date) {
        List<ScreeningEntity> screeningEntities = screeningRepository.findAll();
        List<MovieEntity> dayMovies = screeningEntities.stream().filter(s -> isSameDay(s, date))
                .map(ScreeningEntity::getMovie).distinct().collect(Collectors.toList());
        List<MovieScreeningsDayDto> moviesScreeningsDayDtoList = new ArrayList<>();
        dayMovies.forEach(m -> {
            List<ScreeningResponseDto> screeningsMovieDay = m.getScreenings().stream().filter(s ->
                    isSameDay(s, date)).map(screeningFactory::entityToScreeningResponseDto).collect(Collectors.toList());
            MovieResponseDto movieResponseDto = movieFactory.entityToMovieResponseDto(m);
            moviesScreeningsDayDtoList.add(MovieScreeningsDayDto.builder()
                    .movie(movieResponseDto)
                    .screenings(screeningsMovieDay).build());
        });
        moviesScreeningsDayDtoList.forEach(movieScreening -> {
            List<ScreeningResponseDto> screenings = movieScreening.getScreenings().stream().
                    sorted(Comparator.comparing(ScreeningResponseDto::getStartScreening)).collect(Collectors.toList());
            movieScreening.setScreenings(screenings);
        });
        return moviesScreeningsDayDtoList;
    }

    public ScreeningWithAuditoriumResponseDto getScreeningWithAuditoriumById(Long screeningId) {
        ScreeningEntity screeningEntity = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Screening not found"));
        AuditoriumEntity auditoriumEntity = screeningEntity.getAuditorium();
        return ScreeningWithAuditoriumResponseDto.builder()
                .auditorium(auditoriumFactory.entityToAuditoriumResponseDto(auditoriumEntity))
                .screening(screeningFactory.entityToScreeningResponseDto(screeningEntity))
                .build();
    }

    public List<ScreeningResponseDto> getScreeningsForDayAndAuditorium(LocalDate date, Long auditoriumId) {
        List<ScreeningEntity> screeningEntities = screeningRepository.findAll();
        return screeningEntities.stream()
                .filter(s -> isSameDay(s, date)).filter(s -> s.getAuditorium().getAuditoriumId().equals(auditoriumId))
                .map(screeningFactory::entityToScreeningResponseDto).collect(Collectors.toList());
    }

    private boolean isSameDay(ScreeningEntity s, LocalDate date) {
        return s.getStartScreening().getDayOfMonth() == date.getDayOfMonth() && s.getStartScreening().getMonth() == date.getMonth()
                && s.getStartScreening().getYear() == date.getYear();
    }
}
