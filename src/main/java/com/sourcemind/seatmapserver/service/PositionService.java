package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.model.Position;

import java.util.List;
import java.util.UUID;

public interface PositionService {

    Position findByPositionByUUID(UUID positionUUID);

    Position savePosition(Position request);

    PositionDetailsDTO deletePosition(UUID uuid);

    List<Position> getAllPositions();
}
