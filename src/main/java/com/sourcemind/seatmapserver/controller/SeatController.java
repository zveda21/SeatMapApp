package com.sourcemind.seatmapserver.controller;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.facade.SeatAPIFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {
    private final SeatAPIFacade seatAPIFacade;

    public SeatController(SeatAPIFacade seatAPIFacade) {
        this.seatAPIFacade = seatAPIFacade;
    }


    @PostMapping
    public ResponseEntity<EmployeeSeatAllocationDetailsDTO> registerFloor(@RequestBody EmployeeSeatAllocationDTO request) {
        return ResponseEntity.ok(seatAPIFacade.registerSeatFromDTO(request));
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<EmployeeSeatAllocationDetailsDTO> delete(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(seatAPIFacade.removeSeat(uuid));
    }

    @GetMapping("/{numberOrUUID}")
    public ResponseEntity<EmployeeSeatAllocationDetailsDTO> getByNumberOrUUID(@PathVariable("numberOrUUID") Integer number) {
        return ResponseEntity.ok(seatAPIFacade.findSeatByNumber(number));
    }

    @GetMapping()
    public ResponseEntity<?> findSeat(@RequestParam(value = "employee", required = false) Optional<UUID> employeeUUID,
                                      @RequestParam(value = "floor", required = false) Optional<UUID> floorUUID) {
        if (employeeUUID.isPresent() && floorUUID.isPresent()) {
            return ResponseEntity.badRequest().body("Employee & floor parameters can not be defined at the same time");
        } else if (employeeUUID.isPresent()) {
            return ResponseEntity.ok(seatAPIFacade.findSeatByEmployeeUUID(employeeUUID.get()));
        } else
            return floorUUID.map(uuid -> ResponseEntity.ok(seatAPIFacade.findSeatByFloorUUID(uuid))).orElseGet(() -> ResponseEntity.ok(seatAPIFacade.findAllSeats()));

    }
}
