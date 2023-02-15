package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class EmployeeUUIDNotFoundException extends RuntimeException {
    private final UUID uuid;

    public EmployeeUUIDNotFoundException(UUID id) {
        this.uuid = id;
    }

    public UUID getUuid() {
        return uuid;
    }

}
