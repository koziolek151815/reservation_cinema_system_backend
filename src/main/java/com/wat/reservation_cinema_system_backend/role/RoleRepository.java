package com.wat.reservation_cinema_system_backend.role;


import com.wat.reservation_cinema_system_backend.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findRoleByName(String name);

}
