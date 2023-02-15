package com.sourcemind.seatmapserver.facade;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.mapper.SeatMapper;
import com.sourcemind.seatmapserver.model.SeatAllocation;
import com.sourcemind.seatmapserver.service.EmployeeService;
import com.sourcemind.seatmapserver.service.EmployeeServiceImpl;
import com.sourcemind.seatmapserver.service.SeatService;
import com.sourcemind.seatmapserver.service.SeatServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SeatAPIFacade {

    private final SeatService seatService;
    private final EmployeeService employeeService;

    private final SeatMapper seatMapper;

    public SeatAPIFacade(SeatServiceImpl seatService, EmployeeServiceImpl employeeService, SeatMapper seatMapper) {
        this.seatService = seatService;
        this.employeeService = employeeService;
        this.seatMapper = seatMapper;
    }

    public EmployeeSeatAllocationDetailsDTO registerSeatFromDTO(EmployeeSeatAllocationDTO dto) {
        SeatAllocation seatAllocation = seatService.saveSeatFromAllocationDTO(dto);
        return seatMapper.mapSeatAllocationToDetailsDTO(seatAllocation);
    }

    public EmployeeSeatAllocationDetailsDTO removeSeat(UUID uuid) {
        return seatService.delete(uuid);
    }

    public EmployeeSeatAllocationDetailsDTO findSeatByNumber(Integer number) {
        return seatMapper.mapSeatAllocationToDetailsDTO(seatService.getSeatBySeatNumber(number));
    }

    public List<EmployeeSeatAllocationDetailsDTO> findAllSeats() {
        return seatService.findAllSeats()
                .stream()
                .map(seatMapper::mapSeatAllocationToDetailsDTO)
                .collect(Collectors.toList());
    }

    public EmployeeSeatAllocationDetailsDTO findSeatByEmployeeUUID(UUID uuid) {
        return seatMapper.mapSeatAllocationToDetailsDTO(seatService.getSeatByEmployee(employeeService.findEmployeeByUUId(uuid)));
    }

    public List<EmployeeSeatAllocationDetailsDTO> findSeatByFloorUUID(UUID uuid) {
        return seatService.getSeatBySeatUUID(uuid)
                .stream()
                .map(seatMapper::mapSeatAllocationToDetailsDTO)
                .collect(Collectors.toList());
    }
}
