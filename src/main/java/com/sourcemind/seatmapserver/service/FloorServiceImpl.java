package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.exception.FloorAlreadyExistException;
import com.sourcemind.seatmapserver.exception.FloorNumberNotFoundException;
import com.sourcemind.seatmapserver.exception.FloorUUIDNotFoundException;
import com.sourcemind.seatmapserver.model.Floor;
import com.sourcemind.seatmapserver.repository.FloorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FloorServiceImpl implements FloorService {
    private final FloorRepository floorRepository;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Transactional(readOnly = true)
    public Floor findFloorByUUID(UUID uuid) {

        return floorRepository.findFloorByFloorUUID(uuid).orElseThrow(() -> new FloorUUIDNotFoundException(uuid));
    }

    @Transactional
    public Floor saveFloor(Floor request) {
        try {
            getFloorByNumber(request.getNumber());
            throw new FloorAlreadyExistException(request.getNumber());
        } catch (FloorNumberNotFoundException e) {
            return floorRepository.save(request);
        }

    }

    public FloorDetailsDTO delete(UUID uuid) {
        Floor floor = floorRepository.findFloorByFloorUUID(uuid).orElseThrow(() -> new FloorUUIDNotFoundException(uuid));
        floorRepository.delete(floor);
        return new FloorDetailsDTO();
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public Floor getFloorByNumber(Integer floorNumber) {
        return floorRepository.findFloorByNumber(floorNumber).orElseThrow(() -> new FloorNumberNotFoundException(floorNumber));

    }
}
