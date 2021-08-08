package com.wat.reservation_cinema_system_backend.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long movieId) {
        super("Movie with if  =" + movieId + " not found!");
    }

}
