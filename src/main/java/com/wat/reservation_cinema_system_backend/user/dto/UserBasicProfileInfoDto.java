package com.wat.reservation_cinema_system_backend.user.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserBasicProfileInfoDto {

    private Long id;
    private String username;
    private String email;

}
