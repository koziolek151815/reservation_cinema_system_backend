package com.wat.reservation_cinema_system_backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import com.wat.reservation_cinema_system_backend.screening.ScreeningController;
import com.wat.reservation_cinema_system_backend.screening.ScreeningService;
import com.wat.reservation_cinema_system_backend.screening.dto.MovieScreeningsDayDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class ScreeningControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScreeningService screeningService;

    @Test
    public void should_return_screenings_for_non_logged_users()
            throws Exception {

        List<MovieScreeningsDayDto> movieScreeningsDayDtoArrayList = new ArrayList<>();

        when(screeningService.getMoviesScreeningsForDay(LocalDate.parse("2021-11-17")))
                .thenReturn(movieScreeningsDayDtoArrayList);

        mvc.perform(get("/screenings/list").param("date", "2021-11-17")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(roles = {"worker"})
    public void should_return_screenings_for_auditorium_for_worker() throws Exception {
        List<ScreeningResponseDto> screeningResponseDtoList = new ArrayList<>();
        when(screeningService.getScreeningsForDayAndAuditorium(LocalDate.parse("2021-11-17"), 1L))
                .thenReturn(screeningResponseDtoList);
        mvc.perform(get("/screenings/screeningDayAndAuditorium")
                        .param("date", "2021-11-17").param("auditoriumId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = {"admin"})
    public void should_add_screening_for_worker() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        ScreeningRequestDto screeningRequestDto = ScreeningRequestDto.builder()
                .movieId(1L).auditoriumId(1L).startScreening(LocalDateTime.now().plusHours(4)).
                endScreening(LocalDateTime.now().plusHours(6)).build();

        ScreeningResponseDto screeningResponseDto = ScreeningResponseDto.builder()
                .screeningId(1L).auditoriumId(1L).movieResponseDto(new MovieResponseDto()).build();

        when(screeningService.addScreening(screeningRequestDto)).thenReturn(screeningResponseDto);
        mvc.perform(post("/screenings")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(screeningRequestDto)))
                .andExpect(jsonPath("$.screeningId", is(1)))
                .andExpect(status().isCreated());
    }
}
