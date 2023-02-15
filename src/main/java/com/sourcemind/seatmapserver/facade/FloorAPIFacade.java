package com.sourcemind.seatmapserver.facade;

import com.sourcemind.seatmapserver.dto.FloorDetailsDTO;
import com.sourcemind.seatmapserver.exception.FloorNumberNotFoundException;
import com.sourcemind.seatmapserver.mapper.FloorMapper;
import com.sourcemind.seatmapserver.model.Floor;
import com.sourcemind.seatmapserver.service.FloorService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FloorAPIFacade {
    private final FloorService floorService;
    private final FloorMapper floorMapper;

    public FloorAPIFacade(FloorService floorService, FloorMapper floorMapper) {
        this.floorService = floorService;
        this.floorMapper = floorMapper;
    }

    public FloorDetailsDTO registerFloor(FloorDetailsDTO detailsDTO) {
        Floor floor = floorMapper.mapFloorDetailsToFloor(detailsDTO);
        return floorMapper.mapFloorToFloorDetails(floorService.saveFloor(floor));
    }

    public FloorDetailsDTO updateFloor(UUID floorUUID, FloorDetailsDTO detailsDTO) {
        Floor floor = floorService.findFloorByUUID(floorUUID);
        floor.setNumber(detailsDTO.getNumber());
        return floorMapper.mapFloorToFloorDetails(floorService.saveFloor(floor));
    }

    public FloorDetailsDTO deleteFloor(UUID floorUUID) {
        return floorService.delete(floorUUID);
    }

    public List<FloorDetailsDTO> getAllFloors() {
        List<Floor> allFloors = floorService.getAllFloors();
        return allFloors.stream().map(floorMapper::mapFloorToFloorDetails).collect(Collectors.toList());
    }

    public FloorDetailsDTO getFloorByNumberOrUUID(String number) {
        int floorNumber = -1;
        try {
            floorNumber = Integer.parseInt(number);
            return floorMapper.mapFloorToFloorDetails(floorService.getFloorByNumber(floorNumber));
        } catch (FloorNumberNotFoundException e) {
            throw new FloorNumberNotFoundException(floorNumber);
        } catch (Exception e) {
            return floorMapper.mapFloorToFloorDetails(floorService.findFloorByUUID(UUID.fromString(number)));
        }
    }
}
