package com.sourcemind.seatmapserver.exception;

public class FloorNumberNotFoundException extends RuntimeException {

    private final Integer number;

    public FloorNumberNotFoundException(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
