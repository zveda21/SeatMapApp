package com.sourcemind.seatmapserver.exception;

public class InvalidSeatNumberException extends RuntimeException {
    private final String number;

    public InvalidSeatNumberException(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
