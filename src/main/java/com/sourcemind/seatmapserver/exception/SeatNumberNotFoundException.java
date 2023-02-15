package com.sourcemind.seatmapserver.exception;

public class SeatNumberNotFoundException extends RuntimeException {
    private final Integer number;

    public SeatNumberNotFoundException(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
