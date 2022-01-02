package com.wat.reservation_cinema_system_backend.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    public void addTicketToReservation(@PathVariable Long screeningId, @RequestBody TicketListRequestDto ticketListRequestDto) throws MessagingException {
        ticketService.addTicketToReservation(screeningId,ticketListRequestDto);
    }
    @PreAuthorize("hasRole('worker')")
    @PostMapping("/worker/{screeningId}")
    public void addTicketToReservationWorker(@PathVariable Long screeningId, @RequestBody TicketListRequestDto ticketListRequestDto){
        ticketService.addTicketToReservationWorker(screeningId,ticketListRequestDto);
    }
}
