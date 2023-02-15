package com.sourcemind.seatmapserver.exception;


public class FloorAlreadyExistException extends RuntimeException {
    private final Integer number;

    public FloorAlreadyExistException(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

}
