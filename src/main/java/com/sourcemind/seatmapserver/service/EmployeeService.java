package com.sourcemind.seatmapserver.service;

import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.model.Employee;

import java.util.List;
import java.util.UUID;


public interface EmployeeService {


    Employee create(Employee employee);

    EmployeeDetailsDTO deleteEmployeeByUUID(UUID employeeUuid);

    Employee findEmployeeByUUId(UUID employeeUuid);

    List<Employee> findAllEmployees();

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeeBySearch(String search);
}
