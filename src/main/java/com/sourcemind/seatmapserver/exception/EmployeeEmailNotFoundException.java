package com.sourcemind.seatmapserver.exception;

public class EmployeeEmailNotFoundException extends RuntimeException {
    private final String email;

    public EmployeeEmailNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
