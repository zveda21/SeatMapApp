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
public class SeatAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", updatable = false, unique = true, nullable = false, length = 36)
    private UUID seatUUID = UUID.randomUUID();

    @CreatedDate
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "number", nullable = false)
    private Integer number;

    @JoinColumn(name = "floor_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Floor floor;
    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;

}
