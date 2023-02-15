package com.sourcemind.seatmapserver.controller;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.facade.PositionAPIFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/position")
public class PositionController {
    private final PositionAPIFacade positionAPIFacade;

    public PositionController(PositionAPIFacade positionAPIFacade) {
        this.positionAPIFacade = positionAPIFacade;
    }

    @PostMapping
    public ResponseEntity<PositionDetailsDTO> create(@RequestBody PositionDetailsDTO request) {
        return ResponseEntity.ok(positionAPIFacade.registerPosition(request));
    }

    @GetMapping("")
    public ResponseEntity<List<PositionDetailsDTO>> getAllPositions() {
        return ResponseEntity.ok(positionAPIFacade.getAllPositions());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PositionDetailsDTO> findByUUID(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(positionAPIFacade.findByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PositionDetailsDTO> update(@PathVariable("uuid") UUID uuid, @RequestBody PositionDetailsDTO request) {
        return ResponseEntity.ok(positionAPIFacade.updatePosition(uuid, request));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<PositionDetailsDTO> delete(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(positionAPIFacade.deletePosition(uuid));
    }
}
