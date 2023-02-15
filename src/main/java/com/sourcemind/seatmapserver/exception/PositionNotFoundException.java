package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class PositionNotFoundException extends RuntimeException {
    private final UUID id;

    public PositionNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
