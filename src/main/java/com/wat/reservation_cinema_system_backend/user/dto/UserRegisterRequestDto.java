package com.wat.reservation_cinema_system_backend.user.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserRegisterRequestDto {

    private String username;
    private String password;
    private String email;

    private String avatarUrl;
    private String gender;
    private String profileDescription;

}
