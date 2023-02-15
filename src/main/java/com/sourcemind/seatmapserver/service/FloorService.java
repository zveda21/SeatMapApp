package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.model.Floor;

import java.util.List;
import java.util.UUID;


public interface FloorService {
    Floor findFloorByUUID(UUID uuid);


    Floor saveFloor(Floor request);

    FloorDetailsDTO delete(UUID uuid);

    List<Floor> getAllFloors();

    Floor getFloorByNumber(Integer floorNumber);
}
