package com.sourcemind.seatmapserver.exception;

import java.util.UUID;

public class SeatAlreadyInUseException extends RuntimeException {
    private final Integer seatNumber;
    private final UUID seatUUID;

    public SeatAlreadyInUseException(Integer seatNumber, UUID seatUUID) {
        this.seatNumber = seatNumber;
        this.seatUUID = seatUUID;
    }

    public Integer getNumber() {
        return seatNumber;
    }

    public UUID getSeatUUID() {
        return seatUUID;
    }
}
