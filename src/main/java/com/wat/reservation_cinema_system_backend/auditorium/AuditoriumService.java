package com.wat.reservation_cinema_system_backend.auditorium;

import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumRequestDto;
import com.wat.reservation_cinema_system_backend.auditorium.dto.AuditoriumResponseDto;
import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.SeatEntity;
import com.wat.reservation_cinema_system_backend.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;
    private final AuditoriumFactory auditoriumFactory;
    private final SeatRepository seatRepository;

    public AuditoriumResponseDto addAuditoriumWithSeats(AuditoriumRequestDto auditoriumRequestDto) {
        AuditoriumEntity auditoriumEntity = auditoriumRepository.save(auditoriumFactory.auditoriumRequestDtoToEntity(auditoriumRequestDto));
        for (int row = 1; row <= auditoriumEntity.getRows(); row++) {
            for (int number = 1; number <= auditoriumEntity.getNumbers(); number++) {
                SeatEntity seatEntity = SeatEntity.builder()
                        .auditorium(auditoriumEntity)
                        .numberSeat(number)
                        .rowSeat(row)
                        .build();
                seatRepository.save(seatEntity);
            }
        }
        return auditoriumFactory.entityToAuditoriumResponseDto(auditoriumEntity);
    }

    public List<AuditoriumResponseDto> getAllAuditoriums(){
        return auditoriumRepository.findAll().stream().map(auditoriumFactory::entityToAuditoriumResponseDto).collect(Collectors.toList());
    }
}
