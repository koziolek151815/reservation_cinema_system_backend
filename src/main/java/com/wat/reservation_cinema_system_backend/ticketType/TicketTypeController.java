package com.wat.reservation_cinema_system_backend.ticketType;

import com.wat.reservation_cinema_system_backend.entities.TicketTypeEntity;
import com.wat.reservation_cinema_system_backend.ticket.TicketRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ticketTypes")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;
    private final TicketTypeRepository ticketTypeRepository;

    @GetMapping
    public ResponseEntity<?> getAllTicketTypes() {
        return ResponseEntity.ok(ticketTypeService.getAllTicketTypes());
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public void addTicketType(@RequestBody TicketTypeRequestDto ticketTypeRequestDto) {
        ticketTypeService.addTicketType(ticketTypeRequestDto);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{ticketTypeId}")
    public TicketTypeEntity getTicketTypeById(@PathVariable Long ticketTypeId) {
        return ticketTypeService.getTicketTypeById(ticketTypeId);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{ticketTypeId}")
    public void updateTicketType(@RequestBody TicketTypeRequestDto ticketTypeRequestDto, @PathVariable Long ticketTypeId) {
        ticketTypeService.updateTicketType(ticketTypeRequestDto, ticketTypeId);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public void deleteTicketType(@PathVariable Long id) {
        ticketTypeRepository.deleteById(id);
    }
}
