package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.exception.EmployeeEmailNotFoundException;
import com.sourcemind.seatmapserver.exception.EmployeeUUIDNotFoundException;
import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Transactional
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeDetailsDTO deleteEmployeeByUUID(UUID employeeUuid) {
        Employee employee = employeeRepository.findEmployeeByEmployeeUuid(employeeUuid)
                .orElseThrow(() -> new EmployeeUUIDNotFoundException(employeeUuid));
        employeeRepository.delete(employee);
        return new EmployeeDetailsDTO();
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeByUUId(UUID employeeUuid) {
        return employeeRepository.findEmployeeByEmployeeUuid(employeeUuid)
                .orElseThrow(() -> new EmployeeUUIDNotFoundException(employeeUuid));

    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new EmployeeEmailNotFoundException(email));
    }

    public List<Employee> findEmployeeBySearch(String search) {
        return employeeRepository.findEmployeesByFirstnameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrPositionNameContainingIgnoreCase(search, search, search, search);
    }
}
