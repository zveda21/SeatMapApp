package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.dto.EmployeeRegistrationDTO;
import com.sourcemind.seatmapserver.exception.SeatForEmployeeNotFoundException;
import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.model.SeatAllocation;
import com.sourcemind.seatmapserver.service.EmployeeService;
import com.sourcemind.seatmapserver.service.PositionService;
import com.sourcemind.seatmapserver.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class DefaultEmployeeMapper implements EmployeeMapper {
    private final EmployeeService employeeService;
    private final SeatService seatService;
    private final PositionService positionService;
    private final FloorMapper floorMapper;

    public DefaultEmployeeMapper(EmployeeService employeeService, SeatService seatService, PositionService positionService, FloorMapper floorMapper) {
        this.employeeService = employeeService;
        this.seatService = seatService;
        this.positionService = positionService;
        this.floorMapper = floorMapper;
    }

    @Override
    public Employee mapEmployeeRegistrationDTOToEmployee(EmployeeRegistrationDTO dto) {
        Employee employee = new Employee();
        employee.setEmployeeUuid(UUID.randomUUID());
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());

        employee.setEmail(dto.getEmail());
        if (dto.getManagerUUID() != null)
            employee.setManager(employeeService.findEmployeeByUUId(dto.getManagerUUID()));
        employee.setPosition(positionService.findByPositionByUUID(dto.getPositionUUID()));
        return employee;
    }

    @Override
    public EmployeeDetailsDTO mapEmployeeToDetailsDTO(Employee employee) {
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setUuid(employee.getEmployeeUuid());
        try {
            log.trace("Looking for seat allocation for employee - {}", employee);
            SeatAllocation seatAllocation = seatService.getSeatByEmployee(employee);
            employeeDetailsDTO.setSeatNumber(seatAllocation.getNumber());
            employeeDetailsDTO.setFloor(floorMapper.mapFloorToFloorDetails(seatAllocation.getFloor()));
        } catch (SeatForEmployeeNotFoundException seatUUIDNotFoundException) {
            log.trace("The employee doesn't have seat allocation assigned");
        }
        employeeDetailsDTO.setFirstname(employee.getFirstname());
        employeeDetailsDTO.setLastname(employee.getLastname());
        employeeDetailsDTO.setEmail(employee.getEmail());
        employeeDetailsDTO.setPosition(employee.getPosition().getName());
        if (employee.getManager() != null)
            employeeDetailsDTO.setManagerUUID(employee.getManager().getEmployeeUuid());
        return employeeDetailsDTO;
    }
}
