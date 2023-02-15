package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSeatAllocationDetailsDTO {

    private EmployeeDetailsDTO employee;

    private String seatNumber;

    private FloorDetailsDTO floor;

}
