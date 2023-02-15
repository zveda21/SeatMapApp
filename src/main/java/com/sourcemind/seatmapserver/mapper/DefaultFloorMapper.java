package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class DefaultFloorMapper implements FloorMapper {
    @Override
    public Floor mapFloorDetailsToFloor(FloorDetailsDTO dto) {
        Floor floor = new Floor();
        floor.setNumber(dto.getNumber());
        return floor;
    }

    @Override
    public FloorDetailsDTO mapFloorToFloorDetails(Floor floor) {
        FloorDetailsDTO floorDetailsDTO = new FloorDetailsDTO();
        floorDetailsDTO.setFloorUUID(floor.getFloorUUID());
        floorDetailsDTO.setNumber(floor.getNumber());
        return floorDetailsDTO;
    }
}
