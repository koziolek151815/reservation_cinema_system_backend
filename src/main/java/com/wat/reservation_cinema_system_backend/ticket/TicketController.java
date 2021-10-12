package com.wat.reservation_cinema_system_backend.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    @GetMapping("/{screeningId}")
    public List<TicketResponseDto> getAllTicketsForScreening(@PathVariable Long screeningId){
        return ticketService.getAllTicketsForScreening(screeningId);
    }
    @PostMapping("/{screeningId}")
    public void addTicketToReservation(@PathVariable Long screeningId, @RequestBody TicketListRequestDto ticketListRequestDto){
        ticketService.addTicketToReservation(screeningId,ticketListRequestDto);
    }
}
