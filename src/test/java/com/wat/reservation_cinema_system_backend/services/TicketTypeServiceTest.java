package com.wat.reservation_cinema_system_backend.services;

import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketTypeEntity;
import com.wat.reservation_cinema_system_backend.movie.MovieRepository;
import com.wat.reservation_cinema_system_backend.screening.ScreeningService;
import com.wat.reservation_cinema_system_backend.screening.dto.ScreeningRequestDto;
import com.wat.reservation_cinema_system_backend.ticketType.TicketTypeRepository;
import com.wat.reservation_cinema_system_backend.ticketType.TicketTypeRequestDto;
import com.wat.reservation_cinema_system_backend.ticketType.TicketTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketTypeServiceTest {
    @InjectMocks
    TicketTypeService ticketTypeService;

    @Mock
    TicketTypeRepository ticketTypeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_all_ticket_types() {
        List<TicketTypeEntity> ticketTypeEntityList = new ArrayList<>();
        ticketTypeEntityList.add(TicketTypeEntity.builder().ticketTypeId(1L).name("ulgowy").price(15.32).build());

        when(ticketTypeRepository.findAll()).thenReturn(ticketTypeEntityList);
        List<TicketTypeEntity> ticketTypes = ticketTypeService.getAllTicketTypes();
        verify(ticketTypeRepository, times(1)).findAll();
        assertEquals(15.32, ticketTypes.get(0).getPrice());
    }

    @Test
    public void should_add_ticket_type() {
        TicketTypeRequestDto ticketTypeRequestDto = TicketTypeRequestDto.builder()
                .name("normalny").price(20.32).build();
        ticketTypeService.addTicketType(ticketTypeRequestDto);
        verify(ticketTypeRepository, times(1)).save(any(TicketTypeEntity.class));
    }
}
