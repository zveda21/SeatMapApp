package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PositionDetailsDTO {

    private UUID positionUUID;

    private String name;
}
