package com.sourcemind.seatmapserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FloorDetailsDTO {

    private UUID floorUUID;

    private Integer number;
}
