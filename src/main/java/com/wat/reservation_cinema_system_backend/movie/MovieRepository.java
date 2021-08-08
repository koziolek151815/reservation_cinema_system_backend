package com.wat.reservation_cinema_system_backend.movie;

import com.wat.reservation_cinema_system_backend.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
}
