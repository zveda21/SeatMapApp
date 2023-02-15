package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class SeatForEmployeeNotFoundException extends RuntimeException {
    private final UUID employeeUUID;

    public SeatForEmployeeNotFoundException(UUID employeeUUID) {
        this.employeeUUID = employeeUUID;
    }

    public UUID getEmployeeUUID() {
        return employeeUUID;
    }
}
