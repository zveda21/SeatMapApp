package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.model.SeatAllocation;

import java.util.List;
import java.util.UUID;


public interface SeatService {
    SeatAllocation getSeatBySeatNumber(Integer number);

    SeatAllocation getSeatByEmployee(Employee employee);

    SeatAllocation getSeatByUUID(UUID uuid);

    SeatAllocation saveSeatFromAllocationDTO(EmployeeSeatAllocationDTO dto);

    EmployeeSeatAllocationDetailsDTO delete(UUID seatUUID);

    List<SeatAllocation> findAllSeats();

    List<SeatAllocation> getSeatBySeatUUID(UUID uuid);
}
