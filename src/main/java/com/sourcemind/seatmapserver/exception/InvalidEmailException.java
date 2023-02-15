package com.sourcemind.seatmapserver.exception;

public class InvalidEmailException extends RuntimeException {
    private final String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
