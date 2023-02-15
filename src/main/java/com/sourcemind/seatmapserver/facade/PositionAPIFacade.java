package com.sourcemind.seatmapserver.facade;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.mapper.PositionMapper;
import com.sourcemind.seatmapserver.model.Position;
import com.sourcemind.seatmapserver.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PositionAPIFacade {

    private final PositionService positionService;
    private final PositionMapper mapper;

    public PositionAPIFacade(PositionService positionService, PositionMapper mapper) {
        this.positionService = positionService;
        this.mapper = mapper;
    }

    public PositionDetailsDTO registerPosition(PositionDetailsDTO dto) {
        Position position = mapper.mapDtoToPosition(dto);
        return mapper.mapPositionToDetails(positionService.savePosition(position));
    }

    public PositionDetailsDTO updatePosition(UUID positionUUID, PositionDetailsDTO dto) {
        Position position = positionService.findByPositionByUUID(positionUUID);
        position.setName(dto.getName());
        return mapper.mapPositionToDetails(positionService.savePosition(position));
    }

    public PositionDetailsDTO deletePosition(UUID positionUUID) {
        return positionService.deletePosition(positionUUID);
    }

    public PositionDetailsDTO findByUUID(UUID uuid) {
        return mapper.mapPositionToDetails(positionService.findByPositionByUUID(uuid));
    }

    public List<PositionDetailsDTO> getAllPositions() {
        return positionService.getAllPositions()
                .stream()
                .map(mapper::mapPositionToDetails)
                .collect(Collectors.toList());
    }
}
