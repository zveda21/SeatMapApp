package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.exception.*;
import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.model.SeatAllocation;
import com.sourcemind.seatmapserver.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    private final EmployeeService employeeService;

    private final FloorService floorService;

    public SeatServiceImpl(SeatRepository seatRepository, EmployeeServiceImpl employeeService, FloorServiceImpl floorService) {
        this.seatRepository = seatRepository;
        this.employeeService = employeeService;
        this.floorService = floorService;
    }

    @Transactional(readOnly = true)
    public SeatAllocation getSeatBySeatNumber(Integer number) {
        return seatRepository.findSeatAllocationByNumber(number).orElseThrow(() -> new SeatNumberNotFoundException(number));
    }

    @Transactional(readOnly = true)
    public SeatAllocation getSeatByEmployee(Employee employee) {
        return seatRepository.findSeatAllocationByEmployee(employee).orElseThrow(() -> new SeatForEmployeeNotFoundException(employee.getEmployeeUuid()));
    }

    @Transactional(readOnly = true)
    public SeatAllocation getSeatByUUID(UUID uuid) {
        return seatRepository.findSeatAllocationBySeatUUID(uuid).orElseThrow(() -> new SeatUUIDNotFoundException(uuid));
    }

    @Transactional
    public SeatAllocation saveSeatFromAllocationDTO(EmployeeSeatAllocationDTO dto) {


        int seatNumber;
        try {
            seatNumber = Integer.parseInt(dto.getSeatNumber());
        } catch (Exception e) {
            throw new InvalidSeatNumberException(dto.getSeatNumber());
        }

        Employee employee = employeeService.findEmployeeByUUId(dto.getEmployeeUUID());
        try {
            SeatAllocation seatBySeatNumber = getSeatBySeatNumber(seatNumber);
            if (seatBySeatNumber != null) {
                throw new SeatAlreadyInUseException(seatNumber, seatBySeatNumber.getSeatUUID());
            }

        } catch (SeatNumberNotFoundException seatNumberNotFoundException) {
            log.trace("No Seat allocation found by seat number to remove");
        }
        try {
            SeatAllocation seatByEmployee = getSeatByEmployee(employee);
            if (seatByEmployee != null) {
                seatRepository.delete(seatByEmployee);
                log.info("Removing seat - {}", seatByEmployee.getNumber());
            }
        } catch (SeatForEmployeeNotFoundException seatForEmployeeNotFoundException) {
            log.trace("No Seat allocation found by for employee to remove");
        }
        log.trace("Allocating a new seat");
        SeatAllocation seatAllocation = new SeatAllocation();
        seatAllocation.setEmployee(employee);
        seatAllocation.setFloor(floorService.findFloorByUUID(dto.getFloorUUID()));
        seatAllocation.setNumber(seatNumber);
        return seatRepository.save(seatAllocation);
    }

    public EmployeeSeatAllocationDetailsDTO delete(UUID seatUUID) {
        SeatAllocation seatAllocation = seatRepository.findSeatAllocationBySeatUUID(seatUUID).orElseThrow(() -> new SeatUUIDNotFoundException(seatUUID));
        seatRepository.delete(seatAllocation);
        return new EmployeeSeatAllocationDetailsDTO();
    }

    public List<SeatAllocation> findAllSeats() {
        return seatRepository.findAll();
    }

    public List<SeatAllocation> getSeatBySeatUUID(UUID uuid) {
        return seatRepository.findSeatAllocationsByFloor(floorService.findFloorByUUID(uuid));
    }
}
