package com.wat.reservation_cinema_system_backend.ticketType;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ticketTypes")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @GetMapping
    public ResponseEntity<?> getAllTicketTypes(){
        return ResponseEntity.ok(ticketTypeService.getAllTicketTypes());
    }
}
