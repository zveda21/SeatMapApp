package com.sourcemind.seatmapserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "UUID", updatable = false, unique = true, nullable = false, length = 36)
    private UUID positionUUID = UUID.randomUUID();

    @CreatedDate
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Position(String name) {
        this.name = name;
    }
}
