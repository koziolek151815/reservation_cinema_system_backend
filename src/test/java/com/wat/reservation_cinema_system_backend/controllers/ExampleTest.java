package com.wat.reservation_cinema_system_backend.controllers;

import com.wat.reservation_cinema_system_backend.screening.ScreeningController;
import com.wat.reservation_cinema_system_backend.screening.ScreeningService;
import com.wat.reservation_cinema_system_backend.screening.dto.MovieScreeningsDayDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScreeningService screeningService;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        List<MovieScreeningsDayDto> movieScreeningsDayDtoArrayList = new ArrayList<>();


        given(screeningService.getMoviesScreeningsForDay(LocalDate.parse("2021-10-17"))).willReturn(movieScreeningsDayDtoArrayList);

        mvc.perform(get("/screenings").param("date", "2021-10-17")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
