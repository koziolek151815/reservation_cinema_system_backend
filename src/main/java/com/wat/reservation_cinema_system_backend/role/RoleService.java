package com.wat.reservation_cinema_system_backend.role;


import com.wat.reservation_cinema_system_backend.entities.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service(value = "roleService")
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getUserRole(){
        Optional<RoleEntity> role = roleRepository.findRoleByName("user");
        if(role.isPresent()) return role.get();

        RoleEntity newRole = RoleEntity.builder()
                .name("user")
                .description("test")
                .build();
        roleRepository.save(newRole);
        return newRole;
    }

    public RoleEntity getAdminRole(){
        Optional<RoleEntity> role = roleRepository.findRoleByName("worker");
        if(role.isPresent()) return role.get();

        RoleEntity newRole = RoleEntity.builder()
                .name("worker")
                .description("test")
                .build();
        roleRepository.save(newRole);
        return newRole;
    }
}
