package com.sourcemind.seatmapserver.controller;

import com.sourcemind.seatmapserver.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeUUIDNotFoundException.class)
    public ResponseEntity<ApiFailure> handleEmployeeNotFoundException(EmployeeUUIDNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.employee.not.found",
                        String.format("Employee not found with uuid=%s", ex.getUuid()))
        );
    }

    @ExceptionHandler(SeatUUIDNotFoundException.class)
    public ResponseEntity<ApiFailure> handleSeatNotFoundException(SeatUUIDNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.seat.not.found",
                        String.format("Seat allocation not found with uuid=%s", ex.getId()))
        );
    }

    @ExceptionHandler(PositionNotFoundException.class)
    public ResponseEntity<ApiFailure> handlePositionNotFoundException(PositionNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.position.not.found",
                        String.format("Position not found with uuid=%s", ex.getId()))
        );
    }

    @ExceptionHandler(FloorUUIDNotFoundException.class)
    public ResponseEntity<ApiFailure> handleFloorNotFoundException(FloorUUIDNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.floor.not.found",
                        String.format("Floor not found with uuid=%s", ex.getId()))
        );
    }

    @ExceptionHandler(FloorNumberNotFoundException.class)
    public ResponseEntity<ApiFailure> handleFloorNotFoundException(FloorNumberNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.floor.not.found",
                        String.format("Floor not found with number=%s", ex.getNumber()))
        );
    }

    @ExceptionHandler(SeatAlreadyInUseException.class)
    public ResponseEntity<ApiFailure> handleSeatAlreadyInUseException(SeatAlreadyInUseException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("failure.seat.already.in.use",
                        String.format("Seat number=%s is already in use by seat allocation=%s", ex.getNumber(), ex.getSeatUUID()))
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiFailure> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("illegal.argument.provided",
                        "The provided value is invalid."
                ));
    }

    @ExceptionHandler(FloorAlreadyExistException.class)
    public ResponseEntity<ApiFailure> handleFloorAlreadyExistException(FloorAlreadyExistException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("floor.already.exists",
                        String.format("Floor already exists with number=%s", ex.getNumber()))
        );
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ApiFailure> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("employee.already.exists",
                        String.format("Employee with email=%s already exists", ex.getEmail()))
        );
    }

    @ExceptionHandler(EmployeeEmailNotFoundException.class)
    public ResponseEntity<ApiFailure> handleEmployeeEmailNotFoundException(EmployeeEmailNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("employee.not.found",
                        String.format("Employee with email=%s not found", ex.getEmail()))
        );

    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiFailure> handleInvalidEmailException(InvalidEmailException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("employee.not.found",
                        String.format("Provided email=%s is invalid", ex.getEmail()))
        );

    }

    @ExceptionHandler(SeatNumberNotFoundException.class)
    public ResponseEntity<ApiFailure> handleSeatNumberNotFoundException(SeatNumberNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("seat.not.found",
                        String.format("Seat number=%s is not found", ex.getNumber()))
        );

    }

    @ExceptionHandler(SeatForEmployeeNotFoundException.class)
    public ResponseEntity<ApiFailure> handleSeatForEmployeeNotFoundException(SeatForEmployeeNotFoundException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("seat.not.found",
                        String.format("Seat for employee uuid=%s is not found", ex.getEmployeeUUID()))
        );

    }

    @ExceptionHandler(InvalidSeatNumberException.class)
    public ResponseEntity<ApiFailure> handleInvalidSeatNumberException(InvalidSeatNumberException ex) {

        return ResponseEntity.badRequest().body(
                new ApiFailure("invalid.seat.number",
                        String.format("The seat number should be an integer, but provided=%s", ex.getNumber()))
        );

    }

}
