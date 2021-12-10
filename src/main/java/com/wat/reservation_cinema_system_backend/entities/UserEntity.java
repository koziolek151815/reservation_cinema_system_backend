package com.wat.reservation_cinema_system_backend.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @Column(name = "user_created_date")
    private Instant userCreatedDate;

    @Column(name = "avatar_url")
    private String avatarUrl;
    private String gender;

    @Column(name = "profile_description")
    private String profileDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")})
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy="user")
    private List<ReservationEntity> reservations = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.userCreatedDate = Instant.now();
    }
}
