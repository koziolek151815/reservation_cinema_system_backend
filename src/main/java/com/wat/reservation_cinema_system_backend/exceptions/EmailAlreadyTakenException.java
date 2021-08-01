package com.wat.reservation_cinema_system_backend.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    public  EmailAlreadyTakenException(String email) {
        super("Email  =" + email + " already taken!");
    }

}
