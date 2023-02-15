package com.sourcemind.seatmapserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", updatable = false, unique = true, nullable = false, length = 36)
    private UUID floorUUID = UUID.randomUUID();

    @Column(name = "number", nullable = false)
    private Integer number;

    public Floor(Integer number) {
        this.number = number;
    }
}
