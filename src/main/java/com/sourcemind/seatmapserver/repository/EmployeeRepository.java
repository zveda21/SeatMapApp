package com.sourcemind.seatmapserver.repository;

import com.sourcemind.seatmapserver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmployeeUuid(UUID uuid);

    Optional<Employee> findEmployeeByEmail(String email);

    List<Employee> findEmployeesByFirstnameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrPositionNameContainingIgnoreCase(String firstName, String email, String lastname, String position);
}

