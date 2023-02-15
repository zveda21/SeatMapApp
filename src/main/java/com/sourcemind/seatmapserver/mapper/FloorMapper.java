package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.model.Floor;

public interface FloorMapper {

    Floor mapFloorDetailsToFloor(FloorDetailsDTO dto);

    FloorDetailsDTO mapFloorToFloorDetails(Floor floor);

}
