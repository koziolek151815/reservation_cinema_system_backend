package com.wat.reservation_cinema_system_backend.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@Data
public class UserLoginRequestDto {

    private String email;
    private String password;

}
