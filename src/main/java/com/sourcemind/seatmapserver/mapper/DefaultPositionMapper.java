package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.model.Position;
import org.springframework.stereotype.Component;

@Component
public class DefaultPositionMapper implements PositionMapper {

    @Override
    public Position mapDtoToPosition(PositionDetailsDTO dto) {
        Position position = new Position();
        position.setName(dto.getName());
        return position;
    }

    @Override
    public PositionDetailsDTO mapPositionToDetails(Position position) {
        PositionDetailsDTO positionDetailsDTO = new PositionDetailsDTO();
        positionDetailsDTO.setPositionUUID(position.getPositionUUID());
        positionDetailsDTO.setName(position.getName());
        return positionDetailsDTO;
    }
}
