package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDetailsDTO;
import com.sourcemind.seatmapserver.model.SeatAllocation;

public interface SeatMapper {

    EmployeeSeatAllocationDTO mapSeatAllocationToDTO(SeatAllocation seatAllocation);

    EmployeeSeatAllocationDetailsDTO mapSeatAllocationToDetailsDTO(SeatAllocation seatAllocation);
}
