package com.wat.reservation_cinema_system_backend.repositories;

import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.user.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_check_if_user_exists() {
        UserEntity userEntity = UserEntity.builder()
                .email("abc@gmail.com")
                .password("qwerty")
                .build();
        userRepository.save(userEntity);
        Boolean exists = userRepository.existsByEmail("abc@gmail.com");
        assertTrue(exists);
    }
    @Test
    public void should_find_user_by_email() {
        UserEntity userEntity = UserEntity.builder()
                .email("abc@gmail.com")
                .password("qwerty")
                .build();
        userRepository.save(userEntity);
        UserEntity foundUser = userRepository.findByEmail("abc@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        assertEquals("abc@gmail.com", foundUser.getEmail());
    }

}
