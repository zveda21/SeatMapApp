package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class EmployeeSeatAllocationDTO {

    private UUID employeeUUID;

    private String seatNumber;

    private UUID floorUUID;

}
