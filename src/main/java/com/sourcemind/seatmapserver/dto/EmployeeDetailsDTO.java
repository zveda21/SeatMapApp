package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeDetailsDTO {
    private UUID uuid;

    private String firstname;

    private String lastname;

    private String email;

    private Integer seatNumber;

    private FloorDetailsDTO floor;

    private String position;

    private UUID managerUUID;
}
