package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.PositionDetailsDTO;
import com.sourcemind.seatmapserver.exception.PositionNotFoundException;
import com.sourcemind.seatmapserver.model.Position;
import com.sourcemind.seatmapserver.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepo) {
        this.positionRepository = positionRepo;
    }

    @Transactional(readOnly = true)
    public Position findByPositionByUUID(UUID positionUUID) {
        return positionRepository.findPositionByPositionUUID(positionUUID).orElseThrow(() -> new PositionNotFoundException(positionUUID));
    }

    @Transactional
    public Position savePosition(Position request) {
        return positionRepository.save(request);
    }

    @Transactional
    public PositionDetailsDTO deletePosition(UUID uuid) {
        Position position = findByPositionByUUID(uuid);
        positionRepository.delete(position);
        return new PositionDetailsDTO();
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


}
