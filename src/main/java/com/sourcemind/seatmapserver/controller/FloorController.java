package com.sourcemind.seatmapserver.controller;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.facade.FloorAPIFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/floor")
public class FloorController {
    private final FloorAPIFacade floorAPIFacade;

    public FloorController(FloorAPIFacade floorAPIFacade) {
        this.floorAPIFacade = floorAPIFacade;
    }

    @GetMapping
    public ResponseEntity<List<FloorDetailsDTO>> getAllFloors() {
        return ResponseEntity.ok(floorAPIFacade.getAllFloors());
    }

    @GetMapping("/{numberOrUUID}")
    public ResponseEntity<FloorDetailsDTO> getFloorByNumber(@PathVariable String numberOrUUID) {
        return ResponseEntity.ok(floorAPIFacade.getFloorByNumberOrUUID(numberOrUUID));
    }


    @PostMapping
    public ResponseEntity<FloorDetailsDTO> saveFloor(@RequestBody FloorDetailsDTO request) {
        return ResponseEntity.ok(floorAPIFacade.registerFloor(request));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<FloorDetailsDTO> update(@PathVariable("uuid") UUID uuid, @RequestBody FloorDetailsDTO request) {
        return ResponseEntity.ok(floorAPIFacade.updateFloor(uuid, request));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<FloorDetailsDTO> delete(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(floorAPIFacade.deleteFloor(uuid));
    }
}
