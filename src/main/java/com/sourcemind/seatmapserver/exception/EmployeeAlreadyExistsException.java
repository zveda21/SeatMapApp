package com.sourcemind.seatmapserver.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {
    private final String email;

    public EmployeeAlreadyExistsException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
