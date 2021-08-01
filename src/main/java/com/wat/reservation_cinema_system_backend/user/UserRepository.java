package com.wat.reservation_cinema_system_backend.user;



import com.wat.reservation_cinema_system_backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    void deleteUserEntityByUsername(String test);

    Boolean existsByEmail(String email);
}
