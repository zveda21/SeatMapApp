package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeChangeDTO {

    private String firstname;

    private String lastname;

    private String email;

    private UUID positionUUId;

    private UUID managerUUId;

}
