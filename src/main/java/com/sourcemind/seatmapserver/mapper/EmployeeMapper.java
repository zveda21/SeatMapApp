package com.sourcemind.seatmapserver.mapper;

import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.dto.EmployeeRegistrationDTO;
import com.sourcemind.seatmapserver.model.Employee;

public interface EmployeeMapper {
    Employee mapEmployeeRegistrationDTOToEmployee(EmployeeRegistrationDTO dto);

    EmployeeDetailsDTO mapEmployeeToDetailsDTO(Employee employee);
}
