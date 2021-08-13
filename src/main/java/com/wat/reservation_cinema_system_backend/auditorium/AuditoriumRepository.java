package com.wat.reservation_cinema_system_backend.auditorium;

import com.wat.reservation_cinema_system_backend.entities.AuditoriumEntity;
import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends JpaRepository<AuditoriumEntity,Long> {
}

