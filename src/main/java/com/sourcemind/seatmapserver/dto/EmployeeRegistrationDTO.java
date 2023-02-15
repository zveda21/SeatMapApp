package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeRegistrationDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String seatNumber;

    private UUID floorUUID;

    private UUID positionUUID;

    private UUID managerUUID;
}
