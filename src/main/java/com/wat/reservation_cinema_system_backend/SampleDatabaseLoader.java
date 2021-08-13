package com.wat.reservation_cinema_system_backend;


import com.wat.reservation_cinema_system_backend.auditorium.AuditoriumRepository;
import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.RoleEntity;
import com.wat.reservation_cinema_system_backend.entities.SeatEntity;
import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import com.wat.reservation_cinema_system_backend.role.RoleService;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import com.wat.reservation_cinema_system_backend.seat.SeatRepository;
import com.wat.reservation_cinema_system_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
@Transactional
public class SampleDatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    private final AuditoriumRepository auditoriumRepository;

    public void run(String... strings) {


        UserEntity testUser = getTestUser();

        RoleEntity adminRole = roleService.getAdminRole();
        RoleEntity userRole = roleService.getUserRole();

        Set<RoleEntity> roleEntitySet = new HashSet<>();
        roleEntitySet.add(adminRole);
        roleEntitySet.add(userRole);

        testUser.setRoles(roleEntitySet);

        userRepository.save(testUser);

    }

    private UserEntity getTestUser() {
        return userRepository.findByEmail("test@test.com").orElse(
                UserEntity.builder()
                        .username("test")
                        .email("test@test.com")
                        .avatarUrl("test")
                        .gender("test")
                        .password(bcryptEncoder.encode("test"))
                        .build()
        );
    }




}
