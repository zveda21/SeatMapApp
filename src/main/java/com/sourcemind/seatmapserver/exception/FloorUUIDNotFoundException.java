package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class FloorUUIDNotFoundException extends RuntimeException {

    private final UUID id;

    public FloorUUIDNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
