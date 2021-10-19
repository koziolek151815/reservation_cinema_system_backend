package com.wat.reservation_cinema_system_backend.services;

import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.movie.MovieRepository;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.screening.ScreeningService;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ExampleTest {
    @InjectMocks
    ScreeningService screeningService;

    @Mock
    ScreeningRepository screeningRepository;
    @Mock
    MovieRepository movieRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void createEmployeeTest()
    {
        ScreeningRequestDto screeningRequestDto = ScreeningRequestDto
                .builder()
                .auditoriumId(1L)
                .movieId(1L)
                .startScreening(LocalDateTime.now())
                .endScreening(LocalDateTime.now().plusHours(2))
                .build();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(new MovieEntity()));

        screeningService.addScreening(screeningRequestDto);

        verify(movieRepository, times(1)).findById(1L);
    }
}
