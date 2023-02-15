package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.model.SeatAllocation;
import org.springframework.stereotype.Component;

@Component
public class DefaultSeatMapper implements SeatMapper {
    private final EmployeeMapper employeeMapper;
    private final FloorMapper floorMapper;

    public DefaultSeatMapper(EmployeeMapper employeeMapper, FloorMapper floorMapper) {
        this.employeeMapper = employeeMapper;
        this.floorMapper = floorMapper;
    }

    @Override
    public EmployeeSeatAllocationDTO mapSeatAllocationToDTO(SeatAllocation seatAllocation) {
        EmployeeSeatAllocationDTO employeeSeatAllocationDTO = new EmployeeSeatAllocationDTO();
        employeeSeatAllocationDTO.setFloorUUID(seatAllocation.getFloor().getFloorUUID());
        employeeSeatAllocationDTO.setSeatNumber(String.valueOf(seatAllocation.getNumber()));
        employeeSeatAllocationDTO.setEmployeeUUID(seatAllocation.getEmployee().getEmployeeUuid());
        return employeeSeatAllocationDTO;
    }

    @Override
    public EmployeeSeatAllocationDetailsDTO mapSeatAllocationToDetailsDTO(SeatAllocation seatAllocation) {
        EmployeeSeatAllocationDetailsDTO detailsDTO = new EmployeeSeatAllocationDetailsDTO();
        detailsDTO.setEmployee(employeeMapper.mapEmployeeToDetailsDTO(seatAllocation.getEmployee()));
        detailsDTO.setSeatNumber(String.valueOf(seatAllocation.getNumber()));
        detailsDTO.setFloor(floorMapper.mapFloorToFloorDetails(seatAllocation.getFloor()));
        return detailsDTO;
    }
}
