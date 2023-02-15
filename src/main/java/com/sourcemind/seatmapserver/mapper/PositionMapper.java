package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.model.Position;

public interface PositionMapper {
    Position mapDtoToPosition(PositionDetailsDTO dto);

    PositionDetailsDTO mapPositionToDetails(Position position);
}
