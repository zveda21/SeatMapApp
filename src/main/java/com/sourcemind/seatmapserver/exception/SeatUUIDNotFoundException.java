package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class SeatUUIDNotFoundException extends RuntimeException {
    private final UUID id;

    public SeatUUIDNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
