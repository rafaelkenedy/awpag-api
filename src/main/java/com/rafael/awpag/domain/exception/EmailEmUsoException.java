package com.rafael.awpag.domain.exception;

public class EmailEmUsoException extends RuntimeException {

    public EmailEmUsoException(String email) {
        super("Email" + " '" + email + "' " + "já em uso.");
    }
}
