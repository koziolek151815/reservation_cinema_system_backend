package com.wat.reservation_cinema_system_backend.ticketType;

import com.wat.reservation_cinema_system_backend.entities.TicketEntity;
import com.wat.reservation_cinema_system_backend.entities.TicketTypeEntity;
import com.wat.reservation_cinema_system_backend.ticket.TicketRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeEntity> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    public void addTicketType(TicketTypeRequestDto ticketRequestDto) {
        TicketTypeEntity ticketTypeEntity = TicketTypeEntity.builder()
                .name(ticketRequestDto.getName())
                .price(ticketRequestDto.getPrice())
                .build();
        ticketTypeRepository.save(ticketTypeEntity);
    }
    public TicketTypeEntity getTicketTypeById(Long ticketTypeId){
        return ticketTypeRepository.findById(ticketTypeId).orElseThrow(() -> new RuntimeException("TicketType not found"));
    }
    public void updateTicketType(TicketTypeRequestDto ticketTypeRequestDto, Long ticketTypeId){
        TicketTypeEntity ticketTypeEntity = getTicketTypeById(ticketTypeId);
        ticketTypeEntity.setName(ticketTypeRequestDto.getName());
        ticketTypeEntity.setPrice(ticketTypeEntity.getPrice());
        ticketTypeRepository.save(ticketTypeEntity);
    }
}
