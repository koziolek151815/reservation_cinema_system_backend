package com.wat.reservation_cinema_system_backend.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.reservation_cinema_system_backend.movie.dto.MovieResponseDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningResponseDto;
import com.wat.reservation_cinema_system_backend.ticket.TicketListRequestDto;
import com.wat.reservation_cinema_system_backend.ticket.TicketRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"worker"})
    public void should_book_ticket_worker() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        List<TicketRequestDto> ticketsList = new ArrayList<>();
        ticketsList.add(TicketRequestDto.builder().ticketTypeId(1L).auditoriumId(1L).seatNumber(1).seatRow(1).build());
        TicketListRequestDto ticketListRequestDto = TicketListRequestDto
                .builder().price(10.50).ticketsList(ticketsList)
                .build();

        this.mockMvc.perform(post("/tickets/worker/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ticketListRequestDto)))
                .andExpect(status().is2xxSuccessful());
    }


}

