package com.wat.reservation_cinema_system_backend.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class UserRegisterResponseDto {
    private Long id;
    private String username;
    private String email;
    private Instant userCreatedDate;
}
