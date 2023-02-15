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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "UUID", updatable = false, unique = true, nullable = false, length = 36)
    private UUID employeeUuid = UUID.randomUUID();

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @CreatedDate
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name = "position_id", nullable = true)
    @ManyToOne //TODO: I have removed fetch - lazy as no session is being opened when we get employee
    private Position position;

    @JoinColumn(name = "manager_id", nullable = true)
    @ManyToOne
    private Employee manager;

}
